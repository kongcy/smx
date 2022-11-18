package com.xtxk.system.web;

import com.xtxk.core.Constant;
import com.xtxk.core.annotation.NotNeedLogin;
import com.xtxk.core.annotation.NotNeedPermission;
import com.xtxk.core.encrypt.JwtUtil;
import com.xtxk.core.exception.Assert;
import com.xtxk.core.exception.AuthenticationErrorException;
import com.xtxk.core.exception.BizFeignException;
import com.xtxk.core.exception.ServiceException;
import com.xtxk.core.json.JsonResult;
import com.xtxk.core.json.JsonUtil;
import com.xtxk.core.util.*;
import com.xtxk.core.vo.Payload;
import com.xtxk.core.vo.SysMenu;
import com.xtxk.core.vo.TokenInfo;
import com.xtxk.redis.service.IRedisService;
import com.xtxk.system.api.DTO.FileDTO;
import com.xtxk.system.api.model.SysLog;
import com.xtxk.system.api.model.SysLoginRecord;
import com.xtxk.system.api.model.SysUser;
import com.xtxk.system.api.service.FaceEngineService;
import com.xtxk.system.api.service.SysLogService;
import com.xtxk.system.api.service.SysLoginRecordService;
import com.xtxk.system.api.service.SystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;
import java.util.List;
import static com.xtxk.core.Constant.AUTHOR_TOKEN;
import static com.xtxk.core.json.JsonResult.fail;
import static com.xtxk.core.json.JsonResult.success;

@Api(tags = "登陆服务")
@Controller
@RequestMapping(value = "/system/api")
public class LoginController {
    @Autowired
    private SystemService systemService;
    @Autowired
    private SysLogService logService;
    @Autowired
    private SysLoginRecordService loginRecordService;
    @Autowired
    private IRedisService redisService;
    @Value("${online:false}")
    private boolean online;
    @Autowired
    private FaceEngineService engineService;

    @NotNeedLogin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登陆接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "登陆账号", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "登陆密码", dataType = "string", paramType = "query"),
    })
    public JsonResult login(String username, String password) {
        int state = Constant.LoginSate.FAIL.getCode();
        SysUser user = null;
        try {
            user = systemService.login(username, password);
            Assert.checkNull(user, "登录失败！");
            String token = JwtUtil.createToken(user.getId(), user.getLoginName(), RequestUtils.getRealIp());
            user.setUserToken(token);
            SessionUtils.signIn(user);
            List<SysMenu> menus = systemService.findTreeMenu(user.getId());
            user.setMenus(menus);
            LogUtil.LOG.debug("menus: " + JsonUtil.toJson(menus));
            TokenInfo tokenInfo = new TokenInfo(token, username, RequestUtils.getRealIp(), new Date(), menus);
            redisService.setHashObject(Constant.CACHE_AUTHOR_TOKEN, user.getId(), JsonUtil.toJson(tokenInfo), 0);
            SysLog sysLog = SysLog.builder().loginIp(RequestUtils.getRealIp()).userId(user.getId()).build();
            logService.saveLog(sysLog);
            state = Constant.LoginSate.SUCCESS.getCode();
            return success("登录成功！", user);
        } catch (AuthenticationErrorException e) {
            systemService.lockUser(username, Constant.LoginSate.LOCK.getCode());
            return fail(e.getMessage());
        } catch (ServiceException e) {
            return fail(e.getMessage());
        } catch (BizFeignException e) {
            return fail(e.getMessage());
        } catch (Exception e) {
            LogUtil.LOG.error(e.getMessage());
            return fail("登录异常，请稍后再尝试！");
        } finally {
            saveRecord(username, RequestUtils.getRealIp(), state);
        }
    }

    @NotNeedPermission
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ApiOperation(value = "退出接口")
    public JsonResult logout() {
        String token = RequestUtils.getRequest().getHeader(AUTHOR_TOKEN);
        try {
            String userId = SessionUtils.getUserId();
            if (U.isBlank(token) && U.isNotBlank(userId)) {
                redisService.removeHashObject(Constant.CACHE_AUTHOR_TOKEN, userId);
            } else {
                Payload payload = JwtUtil.verify(token);
                if (payload != null) {
                    String realIp = payload.getLoginIp();
                    if (realIp.equals(RequestUtils.getRealIp())) {
                        String jwtId = payload.getJti();
                        if (U.isNotBlank(jwtId)) {
                            Object ret = redisService.getHashObject(Constant.CACHE_AUTHOR_TOKEN, jwtId);
                            if (ret != null) {
                                TokenInfo tokenInfo = JsonUtil.fromJson(ret.toString(), TokenInfo.class);
                                if (tokenInfo != null && token.equals(tokenInfo.getToken())) {
                                    redisService.removeHashObject(Constant.CACHE_AUTHOR_TOKEN, jwtId);
                                }
                            }
                        }
                    } else {
                        LogUtil.LOG.warn("=====================检查到用户在不同的设备上登录，无法退出！===========================");
                    }
                }
            }
        } catch (Exception e) {
            LogUtil.LOG.error(e.getMessage());
        } finally {
            SessionUtils.signOut();
        }
        return success("退出成功！");
    }

    @NotNeedLogin
    @RequestMapping(value = "/version", method = {RequestMethod.HEAD, RequestMethod.GET})
    @ApiOperation(value = "系统版本")
    public JsonResult changeVersion() {
        return success("版本号更改为: " + RenderViewResolver.changeVersion());
    }


    @NotNeedLogin
    @RequestMapping(value = "/errorCode", method = RequestMethod.GET)
    @ApiOperation(value = "错误码")
    public JsonResult errorCode() {
        return success("错误提示码");
    }

    @NotNeedLogin
    @RequestMapping(value = "/faceLogin",method = RequestMethod.POST)
    @ApiOperation(value = "人脸登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "头像base64字符串", dataType = "string", paramType = "query")
    })
    public JsonResult faceLogin(@RequestParam(required = false) String file){
        return success("登录成功！",engineService.search(new FileDTO(file)));
    }
    /**
     * 检查登录及权限
     */
    @NotNeedLogin
    @RequestMapping(value = "/checkLoginAndPermission", method = {RequestMethod.HEAD, RequestMethod.GET})
    @ApiOperation(value = "检查登录及权限")
    public void checkLoginAndPermission(Object handler) {
    }

    private void saveRecord(String username, String realIp, int state) {
        if (U.isNotBlank(username)) {
            SysLoginRecord record = SysLoginRecord.builder().loginId(U.uuid())
                    .loginIp(realIp)
                    .loginSource(RequestUtils.getOsTypeWithStr())
                    .userId(username)
                    .beginTime(new Date())
                    .status(state).build();
            loginRecordService.saveRecord(record);
        }
    }
}

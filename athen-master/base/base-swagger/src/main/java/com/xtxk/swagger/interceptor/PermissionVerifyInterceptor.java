package com.xtxk.swagger.interceptor;
import com.xtxk.core.annotation.NotNeedLogin;
import com.xtxk.core.annotation.NotNeedPermission;
import com.xtxk.core.encrypt.JwtUtil;
import com.xtxk.core.json.JsonResult;
import com.xtxk.core.json.JsonUtil;
import com.xtxk.core.util.*;
import com.xtxk.core.vo.Payload;
import com.xtxk.core.vo.Permission;
import com.xtxk.core.vo.SysMenu;
import com.xtxk.core.vo.TokenInfo;
import com.xtxk.redis.service.IRedisService;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.xtxk.core.Constant.AUTHOR_TOKEN;
import static com.xtxk.core.Constant.CACHE_AUTHOR_TOKEN;

public class PermissionVerifyInterceptor extends BaseVerifyInterceptor {
   private IRedisService redisService;

    public PermissionVerifyInterceptor() {}

   public PermissionVerifyInterceptor(IRedisService redisService) {
       this.redisService = redisService;
   }



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        return Verify(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
    }

    /**
     * 检查登录及权限
     */
    private boolean Verify(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if(RequestUtils.isMobileRequest()){
            return true;
        }
        if (isServiceTokenAnnotation(handler)) {
            return true;
        }
        if (null != skipAuthUrls && Arrays.asList(skipAuthUrls).contains(request.getRequestURI())) {
            return true;
        }
        // 如果是非线上环境则不验证登录及权限
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 在不需要登录的 url 上标注 @NotNeedLogin
        NotNeedLogin notNeedLogin = getAnnotation(handlerMethod, NotNeedLogin.class);
        // 标注了 NotNeedLogin 且 flag 为 true(默认就是 true)则表示当前的请求不需要验证登录
        if (notNeedLogin != null && notNeedLogin.flag()) {
            return true;
        }
        // 在不需要验证权限的 url 上标注 @NotNeedPermission
        NotNeedPermission notNeedPermission = getAnnotation(handlerMethod, NotNeedPermission.class);
        // 标注了 NotNeedPermission 且 flag 为 true(默认就是 true)则表示当前的请求不需要验证权限
        if (notNeedPermission != null && notNeedPermission.flag()) {
            return true;
        }
        String token = request.getHeader(AUTHOR_TOKEN);
        if (U.isBlank(token)) {
            respError(response, "Token令牌不存在，请先登录！");
            return false;
        }
        Payload payload = JwtUtil.verify(token);
        boolean flag = checkMenuPermission(request.getRequestURI(), token, payload);
        if (!flag) {
            respError(response, "您没有接口访问权限，请联系系统管理员分配操作权限！");
            return false;
        }
        return true;
    }

    private boolean checkMenuPermission(String url, String token, Payload payload) throws IOException {
        if (U.isBlank(url)||U.isBlank(token)) {
            return false;
        }
        if (payload == null || U.isBlank(payload.getJti())) {
            return false;
        }
        String jwtId = payload.getJti();
        Object ret = redisService.getHashObject(CACHE_AUTHOR_TOKEN, jwtId);
        if (ret == null) {
            return false;
        }
        TokenInfo tokenInfo = JsonUtil.fromJson(ret.toString(), TokenInfo.class);
        if (tokenInfo != null && token.equals(tokenInfo.getToken())) {
            // 当前用户如果是超级管理员, 则不需要往下继续验证权限
            if (SessionUtils.isSuper(payload.getIss())) {
                return true;
            }
            List<SysMenu> menus = tokenInfo.getMenus();
            return true;
           // return recursionCheckMenu(url, menus, null);
        }
        return false;
    }

    protected boolean recursionCheckMenu(String url, List<SysMenu> menus, List<SysMenu> childrenMenus) {
        if (A.isNotEmpty(menus)) {
            for (SysMenu menu : menus) {
                if (U.delSuffix(url).equalsIgnoreCase(U.delSuffix(menu.getUrl()))) {
                    String rightId = menu.getRightId();
                    if (U.isNotBlank(rightId) && !rightId.contains(String.valueOf(Permission.NO_PERMISSION.getCode()))) {
                        return true;
                    }
                } else {
                    List<SysMenu> childrenMenu = menu.getChildrenRes();
                    if (A.isNotEmpty(childrenMenu)) {
                        for (SysMenu menu1 : childrenMenu) {
                            if (U.delSuffix(url).equalsIgnoreCase(U.delSuffix(menu1.getUrl()))) {
                                String rightId = menu1.getRightId();
                                if (U.isNotBlank(rightId) && !rightId.contains(String.valueOf(Permission.NO_PERMISSION.getCode()))) {
                                    return true;
                                }
                            } else {
                                List<SysMenu> childrenMenu2 = menu1.getChildrenRes();
                                if (A.isNotEmpty(childrenMenu2)) {
                                    for (SysMenu menu2 : childrenMenu2) {
                                        if (U.delSuffix(url).equalsIgnoreCase(U.delSuffix(menu2.getUrl()))) {
                                            String rightId = menu2.getRightId();
                                            if (U.isNotBlank(rightId) && !rightId.contains(String.valueOf(Permission.NO_PERMISSION.getCode()))) {
                                                return true;
                                            }
                                        } else {
                                            //TODO
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    /**
     * 返回给调用端json字符串
     */
    private void respError(HttpServletResponse response, String msg) throws IOException {
        JsonResult.notPermission(msg).toJson(response);
    }


}

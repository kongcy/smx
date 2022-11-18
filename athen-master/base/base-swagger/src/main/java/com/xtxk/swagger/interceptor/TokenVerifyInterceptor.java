package com.xtxk.swagger.interceptor;

import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.xtxk.core.Constant;
import com.xtxk.core.encrypt.JwtUtil;
import com.xtxk.core.json.JsonResult;
import com.xtxk.core.json.JsonUtil;
import com.xtxk.core.util.LogUtil;
import com.xtxk.core.util.RequestUtils;
import com.xtxk.core.util.U;
import com.xtxk.core.vo.Payload;
import com.xtxk.core.vo.TokenInfo;
import com.xtxk.redis.service.IRedisService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static com.xtxk.core.Constant.AUTHOR_TOKEN;
import static com.xtxk.core.Constant.CACHE_AUTHOR_TOKEN;
import static com.xtxk.core.json.JsonResult.grapLogin;
import static com.xtxk.core.json.JsonResult.notAuth;

/**
 * @Description token验证拦截器
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/5/6
 */
public class TokenVerifyInterceptor extends BaseVerifyInterceptor {
    private IRedisService redisService;

    public TokenVerifyInterceptor() {
    }

    public TokenVerifyInterceptor(IRedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return verifyToken(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Constant.TOKEN_LOCAL.remove();
    }

    /**
     * 验证token是否有效
     */
    protected boolean verifyToken(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LogUtil.LOG.info(RequestUtils.userAgent());
        if(RequestUtils.isMobileRequest()){
            return true;
        }
        if (checkAnnotation(request, handler) || isServiceTokenAnnotation(handler)) {
            return true;
        }
        String token = request.getHeader(AUTHOR_TOKEN);
        if (U.isBlank(token)) {
            respError(response, "Token令牌不存在，请先登录！");
            return false;
        }
        Constant.TOKEN_LOCAL.set(token);
        try {
            Payload payload = JwtUtil.verify(token);
            if (payload != null) {
                String loginIp = payload.getLoginIp();
                if (!loginIp.equals(RequestUtils.getRealIp())) {
                    grapLogin("检查到【TOKEN】已在其它地方使用，为了保障系统安全，请登录后再进行操作！").toJson(response);
                    return false;
                }
                String jwtId = payload.getJti();
                if (U.isNotBlank(jwtId)) {
                    Object ret = redisService.getHashObject(CACHE_AUTHOR_TOKEN, jwtId);
                    if (U.isNotBlank(ret)) {
                        TokenInfo tokenInfo = JsonUtil.fromJson(ret.toString(), TokenInfo.class);
                        if (tokenInfo != null && token.equals(tokenInfo.getToken())) {
                            return true;
                        } else {
                            grapLogin("检查到账户已在其它地方登录，Token已过期无法续约，请重新登录后再进行操作！").toJson(response);
                            return false;
                        }
                    } else {
                        notAuth("Token已失效，请重新登录！").toJson(response);
                        return false;
                    }
                }
            }
        } catch (ExpiredJwtException | TokenExpiredException | InvalidClaimException e) {
            LogUtil.LOG.error(e.getMessage(), e);
            notAuth("Token已失效，请重新登录！").toJson(response);
        } catch (Exception e) {
            LogUtil.LOG.error(e.getMessage(), e);
            respError(response, "Token认证失败，请重新登录！");
        }
        return false;
    }

    /**
     * 返回给调用端json字符串
     */
    protected void respError(HttpServletResponse response, String msg) throws IOException {
        JsonResult.fail(msg).toJson(response);
    }
}

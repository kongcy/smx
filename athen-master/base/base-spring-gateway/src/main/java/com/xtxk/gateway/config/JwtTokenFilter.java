package com.xtxk.gateway.config;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.xtxk.core.Constant;
import com.xtxk.core.encrypt.JwtUtil;
import com.xtxk.core.json.JsonUtil;
import com.xtxk.core.json.ResponseBody;
import com.xtxk.core.util.A;
import com.xtxk.core.util.LogUtil;
import com.xtxk.core.util.U;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import static com.xtxk.core.json.ResponseBody.fail;
import static com.xtxk.core.json.ResponseBody.tokenExpire;

/**
 * @Description 网关只验证token的合理性，具体验证在业务拦截器中进行。
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/4/21
 */
@Component
public class JwtTokenFilter implements GlobalFilter, Ordered {
    private String[] skipAuthUrls = new String[]{"/system/api/login","/system/api/faceLogin"};

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse resp = exchange.getResponse();
        String url = request.getURI().getPath();
        if (null != skipAuthUrls && Arrays.asList(skipAuthUrls).contains(url)) {
            return chain.filter(exchange);
        }
        String token = request.getHeaders().getFirst(Constant.AUTHOR_TOKEN);
        if (U.isBlank(token)) {
            if (A.isNotEmpty(request.getQueryParams())) {
                token = request.getQueryParams().getFirst(Constant.AUTHOR_TOKEN);
            }
        }
        if (U.isBlank(token)) {
            return resultError(resp, ResponseBody.Code.FAIL.getFlag(), "Token令牌不存在，请先登录！");
        }
        try {
            JwtUtil.verify(token);
            return chain.filter(exchange);
        } catch (ExpiredJwtException e) {
            LogUtil.LOG.error(e.getMessage(), e);
            return resultError(resp, ResponseBody.Code.TOKEN_EXPIRE.getFlag(), "Token已过期，请重新登录！");
        } catch (TokenExpiredException e) {
            LogUtil.LOG.error(e.getMessage(), e);
            return resultError(resp, ResponseBody.Code.TOKEN_EXPIRE.getFlag(), "Token已过期，请重新登录！");
        } catch (Exception e) {
            LogUtil.LOG.error(e.getMessage(), e);
            return resultError(resp, ResponseBody.Code.FAIL.getFlag(), "Token认证失败！");
        }
    }

    private Mono<Void> resultError(ServerHttpResponse resp, int code, String message) {
        resp.setStatusCode(HttpStatus.OK);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        DataBuffer buffer = resp.bufferFactory().wrap(JsonUtil.toJson((code == ResponseBody.Code.TOKEN_EXPIRE.getFlag()) ? tokenExpire(message) : fail(message)).getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }

    @Override
    public int getOrder() {
        return -100;
    }
}

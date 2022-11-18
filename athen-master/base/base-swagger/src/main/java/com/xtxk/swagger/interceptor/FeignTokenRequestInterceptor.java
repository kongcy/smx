package com.xtxk.swagger.interceptor;

import com.xtxk.core.util.LogUtil;
import com.xtxk.core.util.RequestUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import javax.servlet.http.HttpServletRequest;
import static com.xtxk.core.Constant.*;

/**
 * @Description feign token 拦截器
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/5/7
 */
public class FeignTokenRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        try{
            HttpServletRequest request = RequestUtils.getRequest();
            if (request != null) {
                String token = request.getHeader(AUTHOR_TOKEN);
                requestTemplate.header(AUTHOR_TOKEN, token);
            }
        }catch (Exception e){
            LogUtil.LOG.error("=======================Feign service 请求添加失败========================"+e.getMessage());
        }
    }
}

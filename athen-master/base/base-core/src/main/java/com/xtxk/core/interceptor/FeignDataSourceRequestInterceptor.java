package com.xtxk.core.interceptor;
import com.xtxk.core.util.RequestUtils;
import com.xtxk.core.util.U;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import javax.servlet.http.HttpServletRequest;

import static com.xtxk.core.Constant.DATA_SOURCE_KEY;
import static com.xtxk.core.Constant.DEFAULT_DB;

/**
 * @Description 数据源key值获取
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/4/8
 */
public class FeignDataSourceRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest request = RequestUtils.getRequest();
        if (request != null) {
            String dataSourceKey=request.getHeader(DATA_SOURCE_KEY);
            if(U.isBlank(dataSourceKey)){
                dataSourceKey= DEFAULT_DB;
            }
            requestTemplate.header(DATA_SOURCE_KEY,dataSourceKey);
        }
    }
}

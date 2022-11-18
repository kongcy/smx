package com.xtxk.core.interceptor;

import com.xtxk.core.Constant;
import com.xtxk.core.util.LogUtil;
import com.xtxk.core.util.RequestUtils;
import com.xtxk.core.util.U;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description feign分页 拦截器(URL上追加分页参数)
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2021/7/12
 */
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        try{
            HttpServletRequest request = RequestUtils.getRequest();
            if (request != null) {
                String currentPage = request.getParameter(Constant.GLOBAL_PAGE);
                String limit = request.getParameter(Constant.GLOBAL_LIMIT);
                if (isPage(currentPage, limit)) {
                    requestTemplate.query(Constant.GLOBAL_PAGE, currentPage);
                    requestTemplate.query(Constant.GLOBAL_LIMIT, limit);
                }
            }
        }catch (Exception e){
            LogUtil.LOG.error("=======================Feign service 请求添加失败========================"+e.getMessage());
        }

    }

    /**
     * 是否为分页
     */
    public boolean isPage(String currentPage, String limit) {
        return (U.isNotBlank(currentPage) && NumberUtils.toInt(currentPage) > 0) && (U.isNotBlank(limit) && NumberUtils.toInt(limit) > 0);
    }
}

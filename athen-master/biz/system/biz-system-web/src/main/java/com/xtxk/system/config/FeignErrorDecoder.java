package com.xtxk.system.config;

import com.xtxk.core.exception.ApiServiceException;
import com.xtxk.core.exception.AuthenticationErrorException;
import com.xtxk.core.exception.BizFeignException;
import com.xtxk.core.exception.FaceEngineServiceException;
import com.xtxk.core.json.JsonUtil;
import com.xtxk.core.util.LogUtil;
import com.xtxk.core.util.U;
import com.xtxk.core.vo.Message;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;

import static com.xtxk.core.Constant.*;

/**
 * @Description feign生产者统一异常处理
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2021/7/22
 */
@Configuration
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        try {
            String message = Util.toString(response.body().asReader());
            Message msg = JsonUtil.fromJson(message, Message.class);
            if (msg != null) {
                if (U.isNotBlank(msg.getMessage())) {
                    String[] arys = msg.getMessage().split(":");
                    if (U.isNotBlank(arys[0])) {
                        if(ERROR_CODE_401.equals(arys[0])){
                            return new AuthenticationErrorException(Integer.parseInt(msg.getStatus()), arys[1]);
                        }else if(ERROR_CODE_402.equals(arys[0])){
                            return new ApiServiceException(arys[1]);
                        }else if(ERROR_CODE_402.equals(arys[0])){
                            return new FaceEngineServiceException(arys[1]);
                        }
                    }
                }
                return new BizFeignException(Integer.parseInt(msg.getStatus()), msg.getMessage());
            }
        } catch (IOException e) {
            LogUtil.LOG.error("FeignErrorDecoder： " + e.getMessage(), e);
        }
        return decode(s, response);
    }
}

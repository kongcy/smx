package com.xtxk.core.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @Description
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/5/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payload implements Serializable {
    /**
     * jwt ID
     */
    private String jti;
    /**
     * 发布时间
     */
    private long iat;
    private String iss;
    private String sub;
    private long exp;
    private String loginIp;
}

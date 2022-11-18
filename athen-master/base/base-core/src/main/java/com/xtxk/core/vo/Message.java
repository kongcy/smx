package com.xtxk.core.vo;

import lombok.Data;
import java.io.Serializable;

/**
 * @Description
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/5/10
 */
@Data
public class Message implements Serializable {
    private String timestamp;
    private String status;
    private String error;
    private String message;
    private String path;
}

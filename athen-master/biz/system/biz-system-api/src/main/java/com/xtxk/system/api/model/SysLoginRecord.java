package com.xtxk.system.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xtxk.core.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "登录记录")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysLoginRecord implements Serializable {
    private static final long serialVersionUID = -6700091770535029218L;
    @ApiModelProperty(value = "主键id")
    private String loginId;
    @ApiModelProperty(value = "用户标识")
    private String userId;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "登录时间")
    private Date beginTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "锁定时间")
    private Date endTime;
    @ApiModelProperty(value = "状态(1成功;0锁定;-1失败)")
    private int status;
    @ApiModelProperty(value = "登录IP")
    private String loginIp;
    @ApiModelProperty(value = "消息")
    private String message;
    @ApiModelProperty(value = "登录来源")
    private String loginSource;

    public boolean isLock() {
        return this.getStatus() == Constant.LoginSate.LOCK.getCode();
    }

    public boolean isFail() {
        return this.getStatus() == Constant.LoginSate.FAIL.getCode();
    }

    public boolean isSuccess() {
        return this.getStatus() == Constant.LoginSate.SUCCESS.getCode();
    }

}
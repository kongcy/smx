package com.xtxk.system.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "系统登录日志")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysLog implements Serializable {
    private static final long serialVersionUID = -4751802631072266784L;
    @ApiModelProperty(value = "主键id")
    private String logId;
    //用户ID
    @ApiModelProperty(value = "用户ID")
    private String userId;
    //登录IP
    @ApiModelProperty(value = "登录IP")
    private String loginIp;
    //登录来源
    @ApiModelProperty(value = "登录来源")
    private String loginSource;
    //生成时间
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "生成时间")
    private Date createTime;
    //登录状态(0 失败 1成功)
    @ApiModelProperty(value = "状态")
    private Integer state;
    //备注
    @ApiModelProperty(value = "消息")
    private String message;

}
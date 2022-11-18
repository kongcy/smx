package com.xtxk.system.api.model;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 系统日志表 --> SYSTEM_LOG
 */
@ApiModel(value = "系统日志")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SystemLog implements Serializable {
    @ApiModelProperty(value = "主键id", hidden = true)
    private String id;

    /**
     * 操作人ID --> USER_ID
     */
    @ApiModelProperty(value = "操作人ID")
    private String userId;

    /**
     * 操作人名 --> USER_NAME
     */
    @ApiModelProperty(value = "操作人名")
    private String userName;

    /**
     * 访问地址 --> IP
     */
    @ApiModelProperty(value = "访问地址")
    private String ip;

    /**
     * 操作内容. 长度在 500 个字以内 --> CONTENT
     */
    @ApiModelProperty(value = "操作内容")
    private String content;

    /**
     * 为 "createTime" 提供查询的起始值
     */
    private Date createTimeStart;
    /**
     * 为 "createTime" 提供查询的结束值
     */
    private Date createTimeEnd;
    /**
     * 创建时间 --> CREATE_TIME
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
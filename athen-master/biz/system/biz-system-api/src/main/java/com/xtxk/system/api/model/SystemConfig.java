package com.xtxk.system.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/** 全局配置表 --> SYSTEM_CONFIG */
@ApiModel(value = "系统配置")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SystemConfig implements Serializable {
    @ApiModelProperty(value = "主键id", hidden = true)
    private String id;

    /** 键 --> CON_KEY */
    @ApiModelProperty(value = "键")
    private String conKey;

    /** 值 --> CON_VALUE */
    @ApiModelProperty(value = "值")
    private String conValue;

    /** 说明 --> CON_COMMENT */
    @ApiModelProperty(value = "说明")
    private String conComment;

}
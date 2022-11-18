package com.athen.business.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "设备归还记录")
public class BizDeviceBackRecord implements Serializable {
    /**
     * 机构id
     */
    @ApiModelProperty(value = "主键id",hidden = true)
    private String id;
    /**
     * 设备编号
     */
    @ApiModelProperty(value = "设备编号")
    private String deviceCode;
    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称")
    private String deviceName;
    /**
     * 归还人
     */
    @ApiModelProperty(value = "归还人")
    private String backUser;
    /**
     * 归还时间
     */
    @ApiModelProperty(value = "归还时间",hidden = true)
    private Date backTime;
    /**
     * 归还数量
     */
    @ApiModelProperty(value = "归还数量")
    private Integer backCount;
    /**
     * 办理人
     */
    @ApiModelProperty(value = "办理人")
    private String operator;

    private String rfid;
    private String userId;

    private static final long serialVersionUID = 1L;

}
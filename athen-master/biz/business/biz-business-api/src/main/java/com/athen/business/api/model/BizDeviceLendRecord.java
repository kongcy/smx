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
@ApiModel(value = "设备借出记录")
public class BizDeviceLendRecord implements Serializable {
    /**
     * 机构id
     */
    @ApiModelProperty(value = "主键id")
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
    private String deviceName="";
    /**
     * 借出时间
     */
    @ApiModelProperty(value = "借出时间")
    private Date beginTime;
    /**
     * 借出人
     */
    @ApiModelProperty(value = "借出人")
    private String lendUser;
    /**
     * 借出部门
     */
    @ApiModelProperty(value = "借出部门")
    private String lendDepart;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String mark;
    /**
     * 经办人
     */
    @ApiModelProperty(value = "经办人")
    private String createUser;
    /**
     * 归还时间
     */
    @ApiModelProperty(value = "归还时间")
    private Date endTime;
    /**
     * 借用数量
     */
    @ApiModelProperty(value = "借用数量")
    private Integer lendCount;

    private String rfid;
    private String userId;
    /**
     * 厂房
     */
    @ApiModelProperty(value = "厂房",hidden = true)
    private String cf="";
    /**
     * 房间
     */
    @ApiModelProperty(value = "房间",hidden = true)
    private String fj="";
    /**
     * 货架
     */
    @ApiModelProperty(value = "货架",hidden = true)
    private String hj="";
    /**
     * 序号
     */
    @ApiModelProperty(value = "序号",hidden = true)
    private String xh="";
    /**
     * 层数
     */
    @ApiModelProperty(value = "层数",hidden = true)
    private String cs="";
    /**
     * 设备类型
     */
    @ApiModelProperty(value = "设备类型",hidden = true)
    private String type="";
    /**
     * 工具类别
     */
    @ApiModelProperty(value = "工具类别",hidden = true)
    private String deviceType="";
    private static final long serialVersionUID = 1L;


}
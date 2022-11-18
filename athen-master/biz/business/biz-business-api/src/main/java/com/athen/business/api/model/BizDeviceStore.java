package com.athen.business.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@ApiModel(value = "设备存储")
public class BizDeviceStore implements Serializable {

    private String rfid;
    /**
     * 工具编号
     */
    @ApiModelProperty(value = "工具编号")
    private String deviceCode;
    /**
     * 厂房
     */
    @ApiModelProperty(value = "厂房")
    private String cf;
    /**
     * 房间
     */
    @ApiModelProperty(value = "房间")
    private String fj;
    /**
     * 货架
     */
    @ApiModelProperty(value = "货架")
    private String hj;
    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private String xh;
    /**
     * 层数
     */
    @ApiModelProperty(value = "层数")
    private String cs;
    /**
     * 库位号
     */
    @ApiModelProperty(value = "库位号")
    private String ckNo;
    /**
     * 检定周期
     */
    @ApiModelProperty(value = "检定周期",hidden = true)
    private Integer checkTime=0;
    /**
     * 检查开始时间
     * */
    @ApiModelProperty(value = "检查开始时间",hidden = true)

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date checkBeginTime;
    /**
     * 检查结束时间
     * */
    @ApiModelProperty(value = "检查结束时间")

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date checkEndTime;

    private static final long serialVersionUID = 1L;

}
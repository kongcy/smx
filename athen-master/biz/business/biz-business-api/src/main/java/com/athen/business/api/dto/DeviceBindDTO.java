package com.athen.business.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chenying
 * @date 2022/10/19 1:13 PM
 * @time 1:13 PM
 * @since 1.0.0
 **/
@Data
public class DeviceBindDTO implements Serializable {
    /**
     * rfid编号
     */
    @ApiModelProperty(value = "rfid编号")
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
    @ApiModelProperty(value = "库位号",hidden = true)
    private String ckNo;

    /**
     * 检查结束时间
     * */
    @ApiModelProperty(value = "检查结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date checkEndTime;

}

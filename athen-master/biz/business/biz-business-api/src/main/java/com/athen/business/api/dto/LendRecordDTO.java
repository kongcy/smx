package com.athen.business.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenying
 * @date 2022/10/18 4:26 PM
 * @time 4:26 PM
 * @since 1.0.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "设备VO")
public class LendRecordDTO implements Serializable {
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
     * userId编号
     */
    @ApiModelProperty(value = "userId编号")
    private String userId;
    /**
     * 当前登陆用户ID
     */
    @ApiModelProperty(value = "当前登陆用户ID",required = true)
    private String currentUser;
}

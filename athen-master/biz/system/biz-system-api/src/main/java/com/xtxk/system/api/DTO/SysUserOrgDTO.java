package com.xtxk.system.api.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/2/28
 */
@ApiModel(value = "用户机构")
@Data
public class SysUserOrgDTO implements Serializable {
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "机构ID")
    private String orgId;
}

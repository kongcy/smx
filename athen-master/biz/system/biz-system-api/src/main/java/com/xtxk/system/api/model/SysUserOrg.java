package com.xtxk.system.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@ApiModel(value = "用户组织")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserOrg implements Serializable {
    @ApiModelProperty(value = "主键id", hidden = true)
    private String id;
    /**
     * 用户ID --> USER_ID
     */
    @ApiModelProperty(value = "用户id")
    private String userId;
    /**
     * 结构ID
     */
    @ApiModelProperty(value = "结构ID")
    private String orgId;

    private static final long serialVersionUID = 1L;


}
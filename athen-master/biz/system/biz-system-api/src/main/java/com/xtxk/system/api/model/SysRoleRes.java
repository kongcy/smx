package com.xtxk.system.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 资源角色 --> SYS_ROLE_RES
 */
@ApiModel(value = "系统资源角色")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SysRoleRes implements Serializable {
    /**
     * 角色ID --> ROLE_ID
     */
    @ApiModelProperty(value = "角色ID")
    private String roleId;

    /**
     * 资源ID --> RES_ID
     */
    @ApiModelProperty(value = "资源ID")
    private String resId;

    /**
     * @see com.xtxk.system.api.model.enums.Permission
     */
    @ApiModelProperty(value = "权限ID")
    private Integer rightId;
}
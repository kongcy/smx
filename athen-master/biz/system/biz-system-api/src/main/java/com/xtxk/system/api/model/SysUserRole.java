package com.xtxk.system.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户角色 --> SYS_USER_ROLE
 */
@ApiModel(value = "用户角色")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SysUserRole implements Serializable {
    @ApiModelProperty(value = "主键id", hidden = true)
    private String id;

    /**
     * 用户ID --> USER_ID
     */
    @ApiModelProperty(value = "用户ID")
    private String userId;

    /**
     * 角色ID --> ROLE_ID
     */
    @ApiModelProperty(value = "角色ID")
    private String roleId;
}
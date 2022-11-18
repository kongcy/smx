package com.xtxk.system.api.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/2/23
 */
@ApiModel(value = "用户角色")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRoleDTO implements Serializable {
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "角色ID集合")
    private List<String> roleIds;
}

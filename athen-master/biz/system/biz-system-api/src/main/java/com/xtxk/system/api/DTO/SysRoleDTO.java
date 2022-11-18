package com.xtxk.system.api.DTO;

import com.google.common.collect.Lists;
import com.xtxk.system.api.model.SysResource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/2/23
 */
@Data
@ApiModel(value = "系统角色")
public class SysRoleDTO implements Serializable {
    @ApiModelProperty(value = "主键id")
    private String id;
    /**
     * 角色名 --> NAME
     */
    @ApiModelProperty(value = "角色名")
    private String name;
    /**
     * 角色编码 --> CODE
     */
    @ApiModelProperty(value = "角色编码")
    private String code;
    /**
     * 角色状态
     */
    @ApiModelProperty(value = "角色状态")
    private Integer state;

    /**
     * 角色描述 --> DESCRIPTION
     */
    @ApiModelProperty(value = "角色描述")
    private String description;

    private List<SysResource> res = Lists.newArrayList();
}

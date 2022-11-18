package com.xtxk.core.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/2/28
 */
@ApiModel(value = "资源菜单")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "主键id", hidden = true)
    private String id;

    /**
     * 资源名称 --> NAME
     */
    @ApiModelProperty(value = "资源名称")
    private String name;

    /**
     * 资源编号 --> CODE
     */
    @ApiModelProperty(value = "资源业务编号")
    private String code;

    /**
     * 父菜单ID --> SUP_ID
     */
    @ApiModelProperty(value = "父菜单ID")
    private String supId;

    /**
     * 菜单路径(id组成) --> PATH
     */
    @ApiModelProperty(value = "菜单接口URL地址")
    private String url;

    /**
     * 菜单级别 --> DEPTH
     */
    @ApiModelProperty(value = "菜单级别")
    private Integer depth;

    /**
     * 操作权限
     */
    @ApiModelProperty(value = "操作权限")
    private String rightId;

    /**
     * 树形子菜单
     */
    @ApiModelProperty(value = "树形子菜单")
    private List<SysMenu> childrenRes = new ArrayList<>();
}

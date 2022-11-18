package com.xtxk.system.api.DTO;

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
 * @Description 菜单DTO
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/2/22
 */
@ApiModel(value = "资源菜单")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysResourceDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "主键id", hidden = true)
    private String id;

    /**
     * 资源名称 --> NAME
     */
    @ApiModelProperty(value = "菜单名称")
    private String name;

    /**
     * 资源编号 --> CODE
     */
    @ApiModelProperty(value = "菜单业务编号")
    private String code;

    /**
     * 父菜单ID --> SUP_ID
     */
    @ApiModelProperty(value = "父菜单ID")
    private String supId;

    /**
     * 菜单路径(id组成) --> PATH
     */
    @ApiModelProperty(value = "菜单路径")
    private String path;

    /**
     * 菜单级别 --> DEPTH
     */
    @ApiModelProperty(value = "菜单级别")
    private Integer depth;

    /**
     * 菜单URL --> URL
     */
    @ApiModelProperty(value = "菜单URL")
    private String url;

    /**
     * 菜单排序 --> SEQ
     */
    @ApiModelProperty(value = "菜单排序")
    private Integer seq;

    /**
     * 菜单图标 --> ICON
     */
    @ApiModelProperty(value = "菜单图标")
    private String icon;

    /**
     * 菜单类型 --> TYPE
     */
    @ApiModelProperty(value = "菜单类型(MENU, URL, BUTTON按钮)")
    private String type;

    /**
     * 操作权限
     */
    @ApiModelProperty(value = "操作权限")
    private String rightId;

    /**
     * 前端对应组件
     */
    @ApiModelProperty(value = "前端对应组件")
    private String webPath = "";

    /**
     * 树形子菜单
     */
    @ApiModelProperty(value = "树形子菜单")
    private List<SysResourceDTO> childrenRes;

}

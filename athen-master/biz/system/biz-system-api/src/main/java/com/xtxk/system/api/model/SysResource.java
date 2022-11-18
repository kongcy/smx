package com.xtxk.system.api.model;

import com.xtxk.core.exception.Assert;
import com.xtxk.system.api.model.enums.State;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 资源菜单 --> SYS_RESOURCE
 */
@ApiModel(value = "资源菜单")
@Data
public class SysResource implements Serializable, Comparable<SysResource> {
    private static final long serialVersionUID = -4511406383899324172L;
    @ApiModelProperty(value = "主键id")
    private String id;

    /**
     * 资源名称 --> NAME
     */
    @ApiModelProperty(value = "资源名称")
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
    @ApiModelProperty(value = "菜单接口路径")
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
     * 为 "createTime" 提供查询的起始值
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTimeStart;
    /**
     * 为 "createTime" 提供查询的结束值
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTimeEnd;
    /**
     * 创建时间 --> CREATE_TIME
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

    /**
     * 创建人 --> CREATE_USER
     */
    @ApiModelProperty(value = "创建人", hidden = true)
    private String createUser;

    /**
     * 为 "updateTime" 提供查询的起始值
     */
    @ApiModelProperty(value = "为 \"updateTime\" 提供查询的起始值", hidden = true)
    private Date updateTimeStart;
    /**
     * 为 "updateTime" 提供查询的结束值
     */
    @ApiModelProperty(value = "updateTime", hidden = true)
    private Date updateTimeEnd;
    /**
     * 更新时间 --> UPDATE_TIME
     */
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date updateTime;

    /**
     * 更新人 --> UPDATE_USER
     */
    @ApiModelProperty(value = "更新人", hidden = true)
    private String updateUser;

    /**
     * 状态(1 启用 0禁用) --> STATE
     */
    @ApiModelProperty(value = "状态(1 启用 0禁用)")
    private Integer state;

    /**
     * 资源操作方法(GET,POST,PUT,DELETE), 多个用逗号分隔, 支持 * 通配 --> METHOD
     */
    @ApiModelProperty(value = "资源操作方法", hidden = true)
    private String method;
    /**
     * 前端对应组件
     */
    @ApiModelProperty(value = "前端对应组件URL")
    private String webPath = "";

    @ApiModelProperty(value = "操作方法集合", hidden = true)
    private List<String> methods;
    /**
     * 操作权限
     */
    @ApiModelProperty(value = "操作权限")
    private String rightId;
    /**
     * 组件
     */
    private String component;


    /**
     * 树形子菜单
     */
    @ApiModelProperty(value = "树形子菜单（选填）", hidden = true)
    private List<SysResource> childrenRes = new ArrayList<>();

    @Override
    public int compareTo(SysResource o) {
        return this.getSeq().compareTo(o.getSeq());
    }

    public void checkDefaultValue() {
        Assert.checkNull(this.getName(), "菜单名称不能为空!");
        Assert.checkNull(this.getCode(), "菜单业务编码不能为空！");
        Assert.checkNull(this.getPath(), "菜单路径不能为空！");
    }

    public enum ResourceType {
        MENU, // 菜单
        URL, // ajax地址
        BUTTON, // 按钮
        MOBILE, API, MODULE, FUNCTION
    }

    public enum MethodType {
        GET, POST, PUT, DELETE
    }
}
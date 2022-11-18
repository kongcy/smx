package com.xtxk.system.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.xtxk.core.vo.SysMenu;
import com.xtxk.system.api.DTO.SysOrgDTO;
import com.xtxk.system.api.DTO.SysRoleDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户 --> SYS_USER
 */
@ApiModel(value = "系统用户")
@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = 433476454507261334L;
    @ApiModelProperty(value = "主键id", hidden = true)
    private String id;

    private String sessionId;

    /**
     * 登录账号 --> LOGIN_NAME
     */
    @ApiModelProperty(value = "登陆账号")
    private String loginName;

    /**
     * 登录密码 --> PASSWORD
     */
    @ApiModelProperty(value = "登录密码")
    private String password;

    /**
     * 用户名 --> USER_NAME
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 手机号 --> PHONE
     */
    @ApiModelProperty(value = "手机号")
    private String phone="";

    /**
     * 邮箱 --> EMAIL
     */
    @ApiModelProperty(value = "邮箱")
    private String email="";

    /**
     * 状态 --> STATE
     * com.xtxk.core.Constant.LoginSate
     */
    @ApiModelProperty(value = "状态(1成功;0锁定;-1停用)")
    private Integer state;

    /**
     * 登录IP --> LOGIN_IP
     */
    @ApiModelProperty(value = "登录IP")
    private Integer loginIp;

    /**
     * 为 "createTime" 提供查询的起始值
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "主键id", hidden = true)
    private Date createTimeStart;
    /**
     * 为 "createTime" 提供查询的结束值
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "主键id", hidden = true)
    private Date createTimeEnd;
    /**
     * 创建时间 --> CREATE_TIME
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;
    /**
     * 创建人 --> CREATE_ID
     */
    @ApiModelProperty(value = "创建人", hidden = true)
    private String createId;
    /**
     * 机构id
     */
    @ApiModelProperty(value = "机构id",hidden = true)
    private String orgId;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别(0 未知 1男 2女)")
    private Integer sex;
    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idCard="";
    /**
     * 头像url
     */
    @ApiModelProperty(value = "头像url")
    private String image="";
    /**
     * 人脸图片
     */
    @ApiModelProperty(value = "人脸图片base64字符串",hidden = true)
    private String base64Photo;
    /**
     * 用户操作柜子权限1,2,3,4
     */
    @ApiModelProperty(value = "用户操作柜子权限1,2,3,4",hidden = true)
    private String cabinLimits;
    /**
     * 用户登录权限禁用开始时间
     */
    @ApiModelProperty(value = "用户登录权限禁用开始时间",hidden = true)
    private Date disstartTime;
    /**
     * 用户登录权限禁用结束时间
     */
    @ApiModelProperty(value = "用户登录权限禁用结束时间",hidden = true)
    private Date disendTime;
    /**
     * 公司
     */
    @ApiModelProperty(value = "公司")
    private String company;
    /**
     * 数据来源
     */
    @ApiModelProperty(value = "数据来源")
    private String source;
    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称",hidden = true)
    private String orgName;

    @ApiModelProperty(value = "用户token",hidden = true)
    private String userToken;

    @ApiModelProperty(value = "机构")
    private List<SysOrgDTO> organizations = Lists.newArrayList();

    @ApiModelProperty(value = "角色ids")
    private List<String> roleIds;

    @ApiModelProperty(value = "角色", hidden = true)
    private List<SysRoleDTO> roles = Lists.newArrayList();

    @ApiModelProperty(value = "资源", hidden = true)
    private List<SysMenu> menus;


    @ApiModelProperty(value = "用户状态",hidden = true)
    private String userState;
}
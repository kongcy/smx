package com.athen.business.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "系统用户")
public class SysUser implements Serializable {
    /**
     * 机构id
     */
    @ApiModelProperty(value = "主键id")
    private String userId;
    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String userName;
    /**
     * 登陆名
     */
    @ApiModelProperty(value = "登陆名")
    private String loginName;
    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码")
    private String password;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer state;
    /**
     * 登录IP
     */
    @ApiModelProperty(value = "登录IP")
    private String loginIp;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createUser;
    /**
     * jwt token
     */
    private String token;

    private static final long serialVersionUID = 1L;

}
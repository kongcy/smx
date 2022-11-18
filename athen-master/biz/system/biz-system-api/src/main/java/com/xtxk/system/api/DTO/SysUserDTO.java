package com.xtxk.system.api.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * @Description 用户DTO
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/2/28
 */
@ApiModel(value = "系统用户")
@Data
public class SysUserDTO implements Serializable {
    @ApiModelProperty(value = "主键id(用户新增不用填，更新必填)")
    private String id;
    /**
     * 登录账号 --> LOGIN_NAME
     */
    @ApiModelProperty(value = "登陆账号(员工号)",required = true)
    private String loginName;

    /**
     * 登录密码 --> PASSWORD
     */
    @ApiModelProperty(value = "登录密码")
    private String password;

    /**
     * 用户名 --> USER_NAME
     */
    @ApiModelProperty(value = "用户名称",required = true)
    private String userName;

    /**
     * 手机号 --> PHONE
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 邮箱 --> EMAIL
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 状态 --> STATE
     */
    @ApiModelProperty(value = "用户状态(0锁定，1启动)",dataType = "1")
    private Integer state;

    /**
     * 登录IP --> LOGIN_IP
     */
    @ApiModelProperty(value = "登录IP",hidden = true)
    private String loginIp;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别(0 未知 1男 2女)")
    private Integer sex;
    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号",required  = true)
    private String idCard;
    /**
     * 头像url
     */
    @ApiModelProperty(value = "头像url")
    private String image;
    /**
     * 机构id
     */
    @ApiModelProperty(value = "机构id")
    private String orgId;
    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色ID集合")
    private List<String> roleIds;
}

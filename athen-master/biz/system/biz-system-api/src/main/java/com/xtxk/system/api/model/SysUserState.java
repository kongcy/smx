package com.xtxk.system.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
/**
 * 用户token表
 * */
@ApiModel(value = "用户token")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SysUserState implements Serializable {

    @ApiModelProperty(value = "主键USER_id")
    private String userId;

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "终端类型(WEB,PC,IOS)")
    private String terminalType;

    @ApiModelProperty(value = "用户状态(1 在线,-1离线)")
    private Integer userState;

    @ApiModelProperty(value = "有效时间")
    private Date validTime;

    @ApiModelProperty(value = "登陆ip")
    private String loginIp;

    @ApiModelProperty(value = "节点IP")
    private String nodeId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建用户")
    private String createUser;

    @ApiModelProperty(value = "更新用户", hidden = true)
    private String updateUser;

    private static final long serialVersionUID = 1L;
}
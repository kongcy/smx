package com.xtxk.system.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xtxk.core.util.A;
import com.xtxk.system.api.DTO.SysResourceDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统角色 --> SYS_ROLE
 */
@ApiModel(value = "系统角色")
@Data
@Builder
public class SysRole implements Serializable {
    @ApiModelProperty(value = "主键id", hidden = true)
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
     * 角色描述 --> DESCRIPTION
     */
    @ApiModelProperty(value = "角色描述")
    private String description;

    /**
     * 创建人 --> CREATE_USER
     */
    @ApiModelProperty(value = "创建人")
    private String createUser;

    /**
     * 为 "createTime" 提供查询的起始值
     */
    private Date createTimeStart;
    /**
     * 为 "createTime" 提供查询的结束值
     */
    private Date createTimeEnd;
    /**
     * 创建时间 --> CREATE_TIME
     */
    @ApiModelProperty(value = "创建时间",hidden = true)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 角色状态
     */
    @ApiModelProperty(value = "角色状态")
    private Integer state;

    @ApiModelProperty(value = "资源ids")
    private String[] resIds;

    private List<SysResourceDTO> res = A.lists();

}
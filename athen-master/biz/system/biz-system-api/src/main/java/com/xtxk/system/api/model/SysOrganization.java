package com.xtxk.system.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "组织结构模型")
public class SysOrganization implements Serializable {
    /**
     * 机构id
     */
    @ApiModelProperty(value = "主键id")
    private String orgId;
    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称")
    private String name;
    /**
     * 机构code
     */
    @ApiModelProperty(value = "机构code")
    private String code="";
    /**
     * 更新日期
     */
    @ApiModelProperty(value = "更新日期",hidden = true)
    private Date updateDate;
    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期",hidden = true)
    private Date createDate;
    /**
     * 启动状态
     */
    @ApiModelProperty(value = "启动状态（1启动 0 锁定）",dataType = "1")
    private int state=1;
    /**
     * 父节点组织
     */
    @ApiModelProperty(value = "父节点组织id")
    private String parentOrgId="";
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;
    /**
     * 组织等级
     */
    @ApiModelProperty(value = "组织等级")
    private String orgLevel;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private int seq;
    /**
     * 父节点组织名称
     */
    @ApiModelProperty(value = "父节点组织名称")
    private String parentOrgName="";

    private static final long serialVersionUID = 1L;
    /**
     * 子组织
     */
    @ApiModelProperty(value = "子目录机构(选填)")
    private List<SysOrganization> childOrgs =new ArrayList<>();

}
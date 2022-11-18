package com.xtxk.system.api.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 组织部门DTO
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/5/17
 */
@Data
@Builder
public class SysOrgDTO implements Serializable {
    @ApiModelProperty(value = "主键id")
    private String orgId;
    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称")
    private String name;
}

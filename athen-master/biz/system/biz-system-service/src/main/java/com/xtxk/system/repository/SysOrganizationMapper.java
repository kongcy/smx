package com.xtxk.system.repository;

import com.xtxk.core.annotation.Page;
import com.xtxk.system.api.DTO.SysOrgDTO;
import com.xtxk.system.api.model.SysOrganization;
import com.xtxk.system.api.model.SysOrganizationExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysOrganizationMapper {
    long countByExample(SysOrganizationExample example);

    int deleteByExample(SysOrganizationExample example);

    int deleteByPrimaryKey(String orgId);

    int insert(SysOrganization record);

    int insertSelective(SysOrganization record);

    @Page
    List<SysOrganization> selectByExample(SysOrganizationExample example);

    SysOrganization selectByPrimaryKey(String orgId);

    int updateByExampleSelective(@Param("record") SysOrganization record, @Param("example") SysOrganizationExample example);

    int updateByExample(@Param("record") SysOrganization record, @Param("example") SysOrganizationExample example);

    int updateByPrimaryKeySelective(SysOrganization record);

    int updateByPrimaryKey(SysOrganization record);

    /**
     * 通过名称查询
     */
    List<SysOrganization> selectByOrgName(String name);

    /**
     * 通过编码查询
     */
    List<SysOrganization> selectByOrgCode(String code);

    /**
     * 查询第一节点机构
     */
    List<SysOrganization> selectSupOrg();

    /**
     * 查询所有子节点
     */
    List<SysOrganization> selectChildrenOrgByParentId(String parentOrgId);

    /**
     * 查找用户组织
     */
    List<SysOrgDTO> findOrgByUserId(String userId);


}
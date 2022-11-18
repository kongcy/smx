package com.xtxk.system.repository;

import com.xtxk.system.api.model.SysUserOrg;
import com.xtxk.system.api.model.SysUserOrgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserOrgMapper {
    long countByExample(SysUserOrgExample example);

    int deleteByExample(SysUserOrgExample example);

    int insert(SysUserOrg record);

    int insertSelective(SysUserOrg record);

    List<SysUserOrg> selectByExample(SysUserOrgExample example);

    int updateByExampleSelective(@Param("record") SysUserOrg record, @Param("example") SysUserOrgExample example);

    int updateByExample(@Param("record") SysUserOrg record, @Param("example") SysUserOrgExample example);

    int deleteByUserId(@Param("userId")String userId);

    int deleteByOrgId(@Param("orgId")String orgId);
}
package com.xtxk.system.repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.xtxk.system.api.model.SysUserRole;
import com.xtxk.system.api.model.SysUserRoleExample;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface SysUserRoleMapper {
    int countByExample(SysUserRoleExample example);

    int deleteByExample(SysUserRoleExample example);

    int deleteByPrimaryKey(String id);

    int insertSelective(SysUserRole record);

    List<SysUserRole> selectByExample(SysUserRoleExample example, PageBounds page);

    List<SysUserRole> selectByExample(SysUserRoleExample example);

    SysUserRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysUserRole record, @Param("example") SysUserRoleExample example);

    int updateByPrimaryKeySelective(SysUserRole record);

    List<String> findRoleByUserId(String userId);

    int deleteRoleByUserId(String userId);

    int deleteByUserIdAndRoleId(@Param("userId")String userId,@Param("roleId")String roleId);
}
package com.xtxk.system.repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.xtxk.core.annotation.Page;
import com.xtxk.system.api.DTO.SysRoleDTO;
import com.xtxk.system.api.model.SysRole;
import com.xtxk.system.api.model.SysRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    int countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    int deleteByPrimaryKey(String id);

    int insertSelective(SysRole record);

    List<SysRole> selectByExample(SysRoleExample example, PageBounds page);

    @Page
    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByPrimaryKeySelective(SysRole record);

    List<String> findUserIdsByRoleId(String roleId);

    /**
     * 角色id查询
     *
     * @param ids 角色id
     */
    List<SysRoleDTO> findRoleIn(@Param("ids") List<String> ids);

    /**
     * 查询用户下所有角色列表
     *
     * @param userId
     */
    List<SysRoleDTO> findRoleByUserId(@Param("userId") String userId);
}
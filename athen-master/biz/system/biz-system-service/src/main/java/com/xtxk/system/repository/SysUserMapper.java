package com.xtxk.system.repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.xtxk.core.annotation.Page;
import com.xtxk.system.api.model.SysUser;
import com.xtxk.system.api.model.SysUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(String id);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example, PageBounds page);

    @Page
    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    @Page
    List<SysUser> findUserInIds(@Param("userIds") List<String> userIds);
    /**
     * 通过登录名查询用户
     * @param loginName 登录名
     */
    List<SysUser> findUserByLoginName(@Param("loginName")String loginName);
    /**
     * 通过组织ID查询用户
     * @param orgId 机构ID
     */
    @Page
    List<SysUser> findUserByOrgId(@Param("orgId")String orgId);

    List<SysUser> findUsers();
    /**
     * 模糊条件查询
     */
    @Page
    List<SysUser> findPageUserByCondition(@Param("condition") String condition);
}
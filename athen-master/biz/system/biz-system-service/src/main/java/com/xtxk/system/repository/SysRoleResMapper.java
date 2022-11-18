package com.xtxk.system.repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.xtxk.system.api.model.SysResource;
import com.xtxk.system.api.model.SysRoleRes;
import com.xtxk.system.api.model.SysRoleResExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleResMapper {
    int countByExample(SysRoleResExample example);

    int deleteByExample(SysRoleResExample example);

    int deleteByPrimaryKey(String id);

    int insertSelective(SysRoleRes record);

    List<SysRoleRes> selectByExample(SysRoleResExample example, PageBounds page);

    List<SysRoleRes> selectByExample(SysRoleResExample example);

    SysRoleRes selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysRoleRes record, @Param("example") SysRoleResExample example);

    int updateByPrimaryKeySelective(SysRoleRes record);

    int deleteRoleResByRoleId(String roleId);

    List<String> findRoleResByRoleId(String roleId);

    List<String> findResIdByUser(String userId);

    /**
     * 获取权限ID
     *
     * @param userId 用户id
     * @param resId  资源id
     */
    List<SysRoleRes> findRightByUserIdAndResId(@Param("userId") String userId, @Param("resId") String resId);

    /**
     * 获取权限ID
     *
     * @param roleId 角色id
     * @param resId  资源id
     */
    List<SysRoleRes> findRightByRoleIdAndResId(@Param("roleId") String roleId, @Param("resId") String resId);
}
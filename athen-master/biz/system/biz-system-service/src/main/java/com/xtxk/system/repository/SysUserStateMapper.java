package com.xtxk.system.repository;

import com.xtxk.system.api.model.SysUserState;
import com.xtxk.system.api.model.SysUserStateExample;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface SysUserStateMapper {
    long countByExample(SysUserStateExample example);

    int deleteByExample(SysUserStateExample example);

    int deleteByPrimaryKey(String userId);

    int insert(SysUserState record);

    int insertSelective(SysUserState record);

    List<SysUserState> selectByExample(SysUserStateExample example);

    SysUserState selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") SysUserState record, @Param("example") SysUserStateExample example);

    int updateByExample(@Param("record") SysUserState record, @Param("example") SysUserStateExample example);

    int updateByPrimaryKeySelective(SysUserState record);

    int updateByPrimaryKey(SysUserState record);
}
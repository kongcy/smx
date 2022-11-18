package com.xtxk.system.repository;

import com.xtxk.core.annotation.Page;
import com.xtxk.system.api.model.SysLoginRecord;
import com.xtxk.system.api.model.SysLoginRecordExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysLoginRecordMapper {
    long countByExample(SysLoginRecordExample example);

    int deleteByExample(SysLoginRecordExample example);

    int deleteByPrimaryKey(String loginId);

    int insert(SysLoginRecord record);

    int insertSelective(SysLoginRecord record);
    @Page
    List<SysLoginRecord> selectByExample(SysLoginRecordExample example);

    SysLoginRecord selectByPrimaryKey(String loginId);

    int updateByExampleSelective(@Param("record") SysLoginRecord record, @Param("example") SysLoginRecordExample example);

    int updateByExample(@Param("record") SysLoginRecord record, @Param("example") SysLoginRecordExample example);

    int updateByPrimaryKeySelective(SysLoginRecord record);

    int updateByPrimaryKey(SysLoginRecord record);

    List<SysLoginRecord> findRecordByLoginTime(@Param("userId")String userId, @Param("beginTime")Date beginTime);

    List<SysLoginRecord> findRecordByUserId(@Param("userId")String userId);
}
package com.xtxk.system.repository;

import com.xtxk.system.api.model.SysOperationLog;
import com.xtxk.system.api.model.SysOperationLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysOperationLogMapper {
    long countByExample(SysOperationLogExample example);

    int deleteByExample(SysOperationLogExample example);

    int deleteByPrimaryKey(String bizId);

    int insert(SysOperationLog record);

    int insertSelective(SysOperationLog record);

    List<SysOperationLog> selectByExample(SysOperationLogExample example);

    SysOperationLog selectByPrimaryKey(String bizId);

    int updateByExampleSelective(@Param("record") SysOperationLog record, @Param("example") SysOperationLogExample example);

    int updateByExample(@Param("record") SysOperationLog record, @Param("example") SysOperationLogExample example);

    int updateByPrimaryKeySelective(SysOperationLog record);

    int updateByPrimaryKey(SysOperationLog record);
}
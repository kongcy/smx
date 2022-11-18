package com.xtxk.system.repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.xtxk.system.api.model.SystemConfig;
import com.xtxk.system.api.model.SystemConfigExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemConfigMapper {
    int countByExample(SystemConfigExample example);

    int deleteByExample(SystemConfigExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(SystemConfig record);

    List<SystemConfig> selectByExample(SystemConfigExample example, PageBounds page);

    List<SystemConfig> selectByExample(SystemConfigExample example);

    SystemConfig selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SystemConfig record, @Param("example") SystemConfigExample example);

    int updateByPrimaryKeySelective(SystemConfig record);

    public SystemConfig selectByKey(String key);
}
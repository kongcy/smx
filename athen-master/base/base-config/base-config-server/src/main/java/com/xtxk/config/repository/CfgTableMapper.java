package com.xtxk.config.repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.xtxk.config.model.CfgTable;
import com.xtxk.config.model.CfgTableExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;

import java.util.List;
import java.util.Map;

public interface CfgTableMapper {
    int countByExample(CfgTableExample example);

    int deleteByExample(CfgTableExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(CfgTable record);

    List<CfgTable> selectByExample(CfgTableExample example, PageBounds page);

    void selectByExample(CfgTableExample example, ResultHandler handler);

    void selectByExample(CfgTableExample example, PageBounds page, ResultHandler handler);

    List<CfgTable> selectByExample(CfgTableExample example);

    CfgTable selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CfgTable record, @Param("example") CfgTableExample example);

    int updateByPrimaryKeySelective(CfgTable record);

    List<Map<String,String>> findTables();

    List<CfgTable> selectByApplicationAndModule(@Param("application") String application,@Param("module")String module,@Param("orderByClause") String orderByClause);
}
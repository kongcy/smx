package com.xtxk.dataSource.provider;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @Description 数据源提供接口
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/3/30
 */
public interface DataSourceProvider {
    /**
     * 加载数据源
     */
    Map<String, DataSource> loadDataSource();
}

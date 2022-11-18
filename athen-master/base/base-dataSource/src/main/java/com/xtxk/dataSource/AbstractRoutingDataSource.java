package com.xtxk.dataSource;

import org.springframework.jdbc.datasource.AbstractDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * @Description 自定义动态数据源
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/3/30
 */
public abstract class AbstractRoutingDataSource extends AbstractDataSource {
    /**
     * 获取数据源
     * @return DataSource
     */
    protected abstract DataSource getDataSource();

    @Override
    public Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getDataSource().getConnection(username,password);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T unwrap(Class<T> iface) throws SQLException {
        if (iface.isInstance(this)) {
            return (T) this;
        }
        return getDataSource().unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return (iface.isInstance(this) || getDataSource().isWrapperFor(iface));
    }
}

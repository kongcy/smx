/* 当前目录会被 mybatis 搜索用来处理类型转换 */
package com.xtxk.system.api.handler;
import com.xtxk.core.util.U;
import com.xtxk.system.api.model.enums.State;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 当前 handle 是自动生成的
 *
 * @see org.apache.ibatis.type.TypeHandlerRegistry
 * @see org.apache.ibatis.type.EnumTypeHandler
 * @see org.apache.ibatis.type.EnumOrdinalTypeHandler
 */
public class StateHandler extends BaseTypeHandler<State> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, State parameter,
                                    JdbcType jdbcType) throws SQLException {
        if (jdbcType == null || jdbcType == JdbcType.VARCHAR) {
            // 如果数据库的字段类型是 varchar, 就使用枚举的 name 来存储
            ps.setString(i, parameter.name());
        } else {
            // 否则使用 getCode 返回的 int 值来存储
            ps.setInt(i, parameter.getCode());
        }
    }

    @Override
    public State getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return U.toEnum(State.class, rs.getObject(columnName));
    }

    @Override
    public State getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return U.toEnum(State.class, rs.getObject(columnIndex));
    }

    @Override
    public State getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return U.toEnum(State.class, cs.getObject(columnIndex));
    }
}

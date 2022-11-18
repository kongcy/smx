package com.xtxk.dataSource.config;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.postgresql.util.PGobject;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/***
 * @description Pg Json 字段类型转换
 * @author liulei
 * @date 2021/10/22 11:17
 */
@MappedTypes(Object.class)
public class JsonTypeHandlerPg  extends BaseTypeHandler<Object> {

    private static final PGobject jsonObject = new PGobject();

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {
        jsonObject.setType("json");
        jsonObject.setValue(o.toString());
        preparedStatement.setObject(i,jsonObject);
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, String s) throws SQLException {
       return resultSet.getObject(s);
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getObject(i);
    }

    @Override
    public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getObject(i);
    }

}

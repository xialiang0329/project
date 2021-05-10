package com.example.demo.common.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeException;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Slf4j
@MappedJdbcTypes(JdbcType.VARCHAR)
public class JsonTypeHandler<T> extends BaseTypeHandler<T> {
    private Class<T> clazz;

    public JsonTypeHandler() {
        this.clazz = clazz;
    }
    public JsonTypeHandler(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T getNullableResult(ResultSet r, String column) throws SQLException {
        return JSON.parseObject(r.getString(column), this.clazz);
    }

    public T getNullableResult(ResultSet r, int column) throws SQLException {
        return JSON.parseObject(r.getString(column), this.clazz);
    }

    public T getNullableResult(CallableStatement r, int column) throws SQLException {
        return JSON.parseObject(r.getString(column), this.clazz);
    }

    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    public void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null) {
            if (jdbcType == null) {
                throw new TypeException("JDBC requires that the JdbcType must be specified for all nullable parameters.");
            }

            try {
                ps.setString(i, "{}");
            } catch (SQLException var7) {
                throw new TypeException("Error setting null for parameter #" + i + " with JdbcType " + jdbcType + " . " + "Try setting a different JdbcType for this parameter or a different jdbcTypeForNull configuration property. " + "Cause: " + var7, var7);
            }
        } else {
            try {
                ps.setString(i, JSON.toJSONString(parameter));
            } catch (Exception var6) {
                throw new TypeException("Error setting non null for parameter #" + i + " with JdbcType " + jdbcType + " . " + "Try setting a different JdbcType for this parameter or a different configuration property. " + "Cause: " + var6, var6);
            }
        }

    }
}
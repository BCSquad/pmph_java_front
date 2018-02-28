package com.bc.pmpheep.back.util;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author liudi
 * @ Mybatis 存取java类到数据库中为json字符串的映射方法
 * @see 参考：https://www.cnblogs.com/kylindai/p/3563818.html
 * @example typeHandler="com.bc.pmpheep.back.util.JsonTypeHandler"
 */
public class JsonTypeHandler extends BaseTypeHandler<Object>{

	@Override
	public Object getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		if (rs.getString(columnName) != null) {
			return JSON.parseObject(rs.getString(columnName), Object.class);
		}else{
			return null;
		}
		
		
	}

	@Override
	public Object getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		if (rs.getString(columnIndex) != null) {
			return JSON.parseObject(rs.getString(columnIndex), Object.class);
		}else{
			return null;
		}
	}

	@Override
	public Object getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		if (cs.getString(columnIndex) != null) {
			return JSON.parseObject(cs.getString(columnIndex), Object.class);
		}else{
			return null;
		}
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			Object parameter, JdbcType jdbcType) throws SQLException {
		 ps.setString(i, JSON.toJSONString(parameter));
	}

}

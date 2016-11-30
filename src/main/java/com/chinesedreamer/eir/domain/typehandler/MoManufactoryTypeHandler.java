package com.chinesedreamer.eir.domain.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.chinesedreamer.eir.domain.constant.MoManufactory;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public class MoManufactoryTypeHandler extends BaseTypeHandler<MoManufactory>{

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, MoManufactory parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter.getCode());
	}

	@Override
	public MoManufactory getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return MoManufactory.get(rs.getString(columnName));
	}

	@Override
	public MoManufactory getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return MoManufactory.get(rs.getString(columnIndex));
	}

	@Override
	public MoManufactory getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return MoManufactory.get(cs.getString(columnIndex));
	}

}

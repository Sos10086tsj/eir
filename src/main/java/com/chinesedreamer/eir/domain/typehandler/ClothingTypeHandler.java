package com.chinesedreamer.eir.domain.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.chinesedreamer.eir.domain.constant.ClothingType;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public class ClothingTypeHandler extends BaseTypeHandler<ClothingType>{

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, ClothingType parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter.getType());
	}

	@Override
	public ClothingType getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return ClothingType.valueOf(rs.getString(columnName));
	}

	@Override
	public ClothingType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return ClothingType.valueOf(rs.getString(columnIndex));
	}

	@Override
	public ClothingType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return ClothingType.valueOf(cs.getString(columnIndex));
	}

}

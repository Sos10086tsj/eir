package com.chinesedreamer.eir.domain.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.chinesedreamer.eir.domain.constant.MoStatus;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public class MoStatusTypeHandler extends BaseTypeHandler<MoStatus>{

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, MoStatus parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setInt(i, parameter.getStatus());
	}

	@Override
	public MoStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return MoStatus.get(rs.getInt(columnName));
	}

	@Override
	public MoStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return MoStatus.get(rs.getInt(columnIndex));
	}

	@Override
	public MoStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return MoStatus.get(cs.getInt(columnIndex));
	}

}

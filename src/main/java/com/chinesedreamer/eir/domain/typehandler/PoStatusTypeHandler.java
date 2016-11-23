package com.chinesedreamer.eir.domain.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.chinesedreamer.eir.domain.constant.PoStatus;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public class PoStatusTypeHandler extends BaseTypeHandler<PoStatus>{

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, PoStatus parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setInt(i, parameter.getStatus());
	}

	@Override
	public PoStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return PoStatus.get(rs.getInt(columnName));
	}

	@Override
	public PoStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return PoStatus.get(rs.getInt(columnIndex));
	}

	@Override
	public PoStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return PoStatus.get(cs.getInt(columnIndex));
	}

}

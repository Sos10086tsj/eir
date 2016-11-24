package com.chinesedreamer.eir.domain.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.chinesedreamer.eir.domain.constant.PoZamzarFileStatus;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public class PoZamzarFileStatusHandler extends BaseTypeHandler<PoZamzarFileStatus>{

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, PoZamzarFileStatus parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setInt(i, parameter.getStatus());
	}

	@Override
	public PoZamzarFileStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return PoZamzarFileStatus.get(rs.getInt(columnName));
	}

	@Override
	public PoZamzarFileStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return PoZamzarFileStatus.get(rs.getInt(columnIndex));
	}

	@Override
	public PoZamzarFileStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return PoZamzarFileStatus.get(cs.getInt(columnIndex));
	}

}

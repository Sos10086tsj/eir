package com.chinesedreamer.eir.domain.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.chinesedreamer.eir.domain.constant.JobPoStatus;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public class JobPoStatusTypeHandler extends BaseTypeHandler<JobPoStatus>{

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, JobPoStatus parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setInt(i, parameter.getStatus());
	}

	@Override
	public JobPoStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return JobPoStatus.get(rs.getInt(columnName));
	}

	@Override
	public JobPoStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return JobPoStatus.get(rs.getInt(columnIndex));
	}

	@Override
	public JobPoStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return JobPoStatus.get(cs.getInt(columnIndex));
	}

}

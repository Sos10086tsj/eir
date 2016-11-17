package com.chinesedreamer.eir.domain.dao;

import java.util.List;

import com.chinesedreamer.eir.domain.model.JobPo;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public interface JobPoDao {
	public int save(JobPo jobPo);
	
	/**
	 * 获取job包含的po list，包含详细文件信息
	 * @param jobId
	 * @return
	 */
	public List<JobPo> findJobPoDetail(Long jobId);
}

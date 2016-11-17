package com.chinesedreamer.eir.service;

import java.util.List;

import com.chinesedreamer.eir.vo.model.job.JobPoVo;
import com.chinesedreamer.eir.vo.model.job.JobVo;
import com.chinesedreamer.eir.vo.query.JobQueryVo;
import com.chinesedreamer.eir.vo.query.Pagination;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 16, 2016
**/
public interface JobService {
	public void save(JobVo vo);
	public void update(JobVo vo);
	
	/**
	 * 上传po文件
	 * @param jobId
	 * @param po
	 */
	public void update2UploadJobPo(Long jobId,Long fileId);
	
	public Pagination<JobVo> getJobs(JobQueryVo queryVo);
	
	/**
	 * 获取job的po list
	 * @param jobId
	 * @return
	 */
	public List<JobPoVo> getJobPos(Long jobId);
}

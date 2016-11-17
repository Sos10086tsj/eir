package com.chinesedreamer.eir.domain.dao;

import java.util.List;

import com.chinesedreamer.eir.domain.model.Job;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public interface JobDao {
	public int save(Job job);
	public int update(Job job);
	public Job findByJobId(String jobId);
	public List<Job> findUserPos(Long userId);
}

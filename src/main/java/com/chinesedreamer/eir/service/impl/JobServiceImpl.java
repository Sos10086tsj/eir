package com.chinesedreamer.eir.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.eir.domain.dao.JobDao;
import com.chinesedreamer.eir.domain.dao.JobPoDao;
import com.chinesedreamer.eir.domain.model.Job;
import com.chinesedreamer.eir.domain.model.JobPo;
import com.chinesedreamer.eir.service.JobService;
import com.chinesedreamer.eir.util.IdUtil;
import com.chinesedreamer.eir.util.SessionUtil;
import com.chinesedreamer.eir.vo.model.job.JobPoVo;
import com.chinesedreamer.eir.vo.model.job.JobVo;
import com.chinesedreamer.eir.vo.query.JobQueryVo;
import com.chinesedreamer.eir.vo.query.Pagination;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
@Service
public class JobServiceImpl implements JobService{
	@Resource
	private JobDao dao;
	@Resource
	private JobPoDao jobPoDao;

	@Override
	public void save(JobVo vo) {
		Job job = new Job();
		job.setCreateDate(new Date());
		job.setName(vo.getName());
		job.setJobId(IdUtil.generateJobId());
		job.setCreateUser(SessionUtil.getUserId());
		this.dao.save(job);
	}

	@Override
	public void update(JobVo vo) {
		Job job = this.dao.findByJobId(vo.getJobId());
		job.setName(vo.getName());
		this.dao.update(job);
	}

	@Override
	public void update2UploadJobPo(Long jobId,Long fileId) {
		JobPo jobPo = new JobPo();
		jobPo.setJobId(jobId);
		jobPo.setPoId(fileId);
		this.jobPoDao.save(jobPo);
	}

	@Override
	public List<JobPoVo> getJobPos(Long jobId) {
		List<JobPo> jobPos = this.jobPoDao.findJobPoDetail(jobId);
		List<JobPoVo> vos = new ArrayList<JobPoVo>();
		for (JobPo jobPo : jobPos) {
			JobPoVo vo = new JobPoVo();
			vo.setPoDate(jobPo.getPoDate());
			vo.setPoId(jobPo.getPoId());
			vo.setPoName(jobPo.getPoName());
			vo.setPoPath(jobPo.getPoPath());
			vo.setPoStatus(jobPo.getStatus().getStatus());
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public Pagination<JobVo> getJobs(JobQueryVo queryVo) {
		Pagination<JobVo> pagination = new Pagination<JobVo>();
		pagination.setPageNum(queryVo.getPageNum());
		pagination.setPageSize(queryVo.getPageSize());
		
		PageHelper.startPage(queryVo.getPageNum(), queryVo.getPageSize());
		List<Job> jobs = this.dao.findUserPos(SessionUtil.getUserId());
		
		List<JobVo> vos = new ArrayList<JobVo>();
		for (Job job : jobs) {
			JobVo vo = new JobVo();
			vo.setJobId(job.getJobId());
			vo.setName(job.getName());
			vo.setCreateDate(job.getCreateDate());
			vo.setId(job.getId());
			vos.add(vo);
		}
		
		pagination.setData(vos);
		
		Page<Job> page = (Page<Job>)jobs;
		pagination.setTotal(page.getTotal());
		pagination.setTotalPage(page.getPages());
		return pagination;
	}

}

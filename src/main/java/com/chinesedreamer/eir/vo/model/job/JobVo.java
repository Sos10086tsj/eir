package com.chinesedreamer.eir.vo.model.job;

import java.util.Date;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 16, 2016
**/
public class JobVo {
	private Long id;
	private String jobId;
	private String name;
	private Date createDate;
	public String getJobId() {
		return jobId;
	}
	public String getName() {
		return name;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}

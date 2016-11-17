package com.chinesedreamer.eir.domain.model;

import java.util.Date;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 16, 2016
**/
public class Job extends BaseModel{
	private String jobId;
	private String name;
	private Date createDate;
	private Long createUser;
	public String getJobId() {
		return jobId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public Long getCreateUser() {
		return createUser;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}

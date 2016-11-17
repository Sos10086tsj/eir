package com.chinesedreamer.eir.domain.model;

import java.util.Date;

import com.chinesedreamer.eir.domain.constant.JobPoStatus;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public class JobPo {
	private Long jobId;
	private Long poId;
	private JobPoStatus status;
	
	/**扩展属性**/
	private String poName;
	private String poPath;
	private Date poDate;
	public Long getJobId() {
		return jobId;
	}
	public Long getPoId() {
		return poId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	public void setPoId(Long poId) {
		this.poId = poId;
	}
	public JobPoStatus getStatus() {
		return status;
	}
	public void setStatus(JobPoStatus status) {
		this.status = status;
	}
	public String getPoName() {
		return poName;
	}
	public String getPoPath() {
		return poPath;
	}
	public Date getPoDate() {
		return poDate;
	}
	public void setPoName(String poName) {
		this.poName = poName;
	}
	public void setPoPath(String poPath) {
		this.poPath = poPath;
	}
	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}
	
	
}

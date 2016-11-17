package com.chinesedreamer.eir.vo.model.job;

import java.util.Date;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public class JobPoVo {
	private Long poId;
	private String poName;
	private Date poDate;
	private Integer poStatus;
	private String poPath;
	public Long getPoId() {
		return poId;
	}
	public String getPoName() {
		return poName;
	}
	public Date getPoDate() {
		return poDate;
	}
	public Integer getPoStatus() {
		return poStatus;
	}
	public String getPoPath() {
		return poPath;
	}
	public void setPoId(Long poId) {
		this.poId = poId;
	}
	public void setPoName(String poName) {
		this.poName = poName;
	}
	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}
	public void setPoStatus(Integer poStatus) {
		this.poStatus = poStatus;
	}
	public void setPoPath(String poPath) {
		this.poPath = poPath;
	}
	
	
}

package com.chinesedreamer.eir.domain.model;

import java.util.Date;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 24, 2016
**/
public class PoZamzarHistory extends BaseModel{
	private Long poId;
	private Date parseDate = new Date();
	private String jobId;
	private String responseContent;
	private Boolean success = Boolean.TRUE;
	private String errorMessage;
	private Boolean fileDownloaded;
	public Long getPoId() {
		return poId;
	}
	public Date getParseDate() {
		return parseDate;
	}
	public String getResponseContent() {
		return responseContent;
	}
	public Boolean getSuccess() {
		return success;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setPoId(Long poId) {
		this.poId = poId;
	}
	public void setParseDate(Date parseDate) {
		this.parseDate = parseDate;
	}
	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public Boolean getFileDownloaded() {
		return fileDownloaded;
	}
	public void setFileDownloaded(Boolean fileDownloaded) {
		this.fileDownloaded = fileDownloaded;
	}
	
	
}

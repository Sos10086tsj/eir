package com.chinesedreamer.eir.domain.model;

import java.util.Date;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 24, 2016
**/
public class PoZamzarFile extends BaseModel{
	private Long historyId;
	private String zamzarFileId;
	private Long fileId;
	private Date createDate = new Date();
	public Long getHistoryId() {
		return historyId;
	}
	public String getZamzarFileId() {
		return zamzarFileId;
	}
	public Long getFileId() {
		return fileId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}
	public void setZamzarFileId(String zamzarFileId) {
		this.zamzarFileId = zamzarFileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}

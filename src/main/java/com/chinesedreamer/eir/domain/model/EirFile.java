package com.chinesedreamer.eir.domain.model;

import java.util.Date;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public class EirFile extends BaseModel{
	private String fileName;
	private String filePath;
	private Date uploadDate;
	private Long uploadUser;
	public String getFileName() {
		return fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public Long getUploadUser() {
		return uploadUser;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public void setUploadUser(Long uploadUser) {
		this.uploadUser = uploadUser;
	}
	
	
}

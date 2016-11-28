package com.chinesedreamer.eir.vo.model.po;

import java.util.Date;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 23, 2016
**/
public class PoVo {
	private Long id;
	private Long fileId;
	private String fileName;
	private String filePath;
	private String clothingType;
	private String status;
	private Date uploadDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFileId() {
		return fileId;
	}
	public String getClothingType() {
		return clothingType;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public void setClothingType(String clothingType) {
		this.clothingType = clothingType;
	}
	public String getFileName() {
		return fileName;
	}
	public String getStatus() {
		return status;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	
}

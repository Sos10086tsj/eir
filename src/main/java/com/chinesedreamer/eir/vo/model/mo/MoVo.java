package com.chinesedreamer.eir.vo.model.mo;

import java.util.Date;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 29, 2016
**/
public class MoVo {
	private Long id;
	private Long fileId;
	private String fileName;
	private String filePath;
	private String clothingType;
	private String status;
	private Date uploadDate;
	private String manufactory;
	public Long getId() {
		return id;
	}
	public Long getFileId() {
		return fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public String getClothingType() {
		return clothingType;
	}
	public String getStatus() {
		return status;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public String getManufactory() {
		return manufactory;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public void setClothingType(String clothingType) {
		this.clothingType = clothingType;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public void setManufactory(String manufactory) {
		this.manufactory = manufactory;
	}
	
	
}

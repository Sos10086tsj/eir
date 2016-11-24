package com.chinesedreamer.eir.tools.zamazar.model;
/**
 * Description:
 * job response
 * 
 * status response
 * {
    "id" : 15,
    "key" : "GiVUYsF4A8ssq93FR48H",
    "status" : "successful",
    "sandbox" : true,
    "created_at" : "2013-10-27T13:41:00Z",
    "finished_at" : "2013-10-27T13:41:13Z",
    "source_file" : {"id":2,"name":"portrait.gif","size":90571},
    "target_files" : [{"id":3,"name":"portrait.png","size":15311}],
    "target_format" : "png",
    "credit_cost" : 1
	}
 * Auth:Paris
 * Date:Nov 24, 2016
**/
public class ZamzarResponse {
	private Boolean success = Boolean.TRUE;
	private String id;
	private String key;
	private String status;
	private Boolean sandbox;
	private String createdDate;
	private String finishedDate;
	private String sourceFile;
	private String targetFiles;
	private String targetFormat;
	private String creditCost;
	
	private String errors;

	public Boolean getSuccess() {
		return success;
	}

	public String getId() {
		return id;
	}

	public String getKey() {
		return key;
	}

	public String getStatus() {
		return status;
	}

	public Boolean getSandbox() {
		return sandbox;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public String getFinishedDate() {
		return finishedDate;
	}

	public String getSourceFile() {
		return sourceFile;
	}

	public String getTargetFiles() {
		return targetFiles;
	}

	public String getTargetFormat() {
		return targetFormat;
	}

	public String getCreditCost() {
		return creditCost;
	}

	public String getErrors() {
		return errors;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setSandbox(Boolean sandbox) {
		this.sandbox = sandbox;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public void setFinishedDate(String finishedDate) {
		this.finishedDate = finishedDate;
	}

	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}

	public void setTargetFiles(String targetFiles) {
		this.targetFiles = targetFiles;
	}

	public void setTargetFormat(String targetFormat) {
		this.targetFormat = targetFormat;
	}

	public void setCreditCost(String creditCost) {
		this.creditCost = creditCost;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}
	
	
}

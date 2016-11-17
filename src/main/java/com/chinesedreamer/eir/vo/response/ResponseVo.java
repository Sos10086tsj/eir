package com.chinesedreamer.eir.vo.response;
/**
 * Description:
 * Auth:Paris
 * Date:Nov 15, 2016
**/
public class ResponseVo {
	private Boolean success;
	private String errorCode;
	private String errorMessage;
	private Object data;
	public Boolean getSuccess() {
		return success;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public Object getData() {
		return data;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}

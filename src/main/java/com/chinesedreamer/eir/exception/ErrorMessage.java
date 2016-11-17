package com.chinesedreamer.eir.exception;
/**
 * Description:
 * Auth:Paris
 * Date:Apr 1, 2016
**/
public class ErrorMessage {
	private String code;
	private String message;
	
	public ErrorMessage(String code,String message){
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}

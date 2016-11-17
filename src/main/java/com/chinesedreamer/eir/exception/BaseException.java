package com.chinesedreamer.eir.exception;

public abstract class BaseException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7680413775722688498L;
	
	protected String code;
	protected String message;
	public BaseException(String message, Throwable cause){
		super(message, cause);
		this.message = message;
	}
	public BaseException(String message){
		super(message);
		this.message = message;
	}
	public BaseException(String message, String code){
		super(message);
		this.message = message;
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
	public BaseException(ErrorMessage errorMessage){
		super(errorMessage.getMessage());
		this.message = errorMessage.getMessage();
		this.code = errorMessage.getCode();
	}
}

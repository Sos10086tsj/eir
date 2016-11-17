package com.chinesedreamer.eir.exception;
/**
 * Description:
 * Auth:Paris
 * Date:Nov 16, 2016
**/
public class ErrorMessageCode {
	public static class USER{
		public static final String CODE = "USER";
		public static final String USERNAME_PASS_NOT_MATCH = "USERNAME_PASS_NOT_MATCH";
		public static final String USER_NOT_EXIST = "USER_NOT_EXIST";
		public static final String USER_REGISTER_FAILURE = "USER_REGISTER_FAILURE";
	}
	
	public static class SYSTEM{
		public static final String UNEXPECTED_ERROR_MESSAGE_CODE = "S10001";
		public static final String UNEXPECTED_ERROR_MESSAGE_MSG = "未知错误";
		
		public static final String SESSION_OVERDUE_CODE = "S10002";
		public static final String SESSION_OVERDUE_MESSAGE = "登陆失效，请重新登录";
	}
	
	public static class FILE{
		public static final String CODE = "FILE";
		public static final String SAVE_FAILURE = "SAVE_FAILURE";
	}
}

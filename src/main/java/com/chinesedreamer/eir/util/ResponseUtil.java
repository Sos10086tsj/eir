package com.chinesedreamer.eir.util;

import com.chinesedreamer.eir.vo.response.ResponseVo;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 16, 2016
**/
public class ResponseUtil {
	public static ResponseVo success() {
		ResponseVo vo = new ResponseVo();
		vo.setSuccess(Boolean.TRUE);
		return vo;
	}
	
	public static ResponseVo success(Object data) {
		ResponseVo vo = new ResponseVo();
		vo.setSuccess(Boolean.TRUE);
		vo.setData(data);
		return vo;
	}
	
	public static ResponseVo failure(String code, String message){
		ResponseVo vo = new ResponseVo();
		vo.setErrorCode(code);
		vo.setErrorMessage(message);
		vo.setSuccess(Boolean.FALSE);
		return vo;
	}
	
	public static ResponseVo failure(String code, String message,Object data){
		ResponseVo vo = new ResponseVo();
		vo.setErrorCode(code);
		vo.setErrorMessage(message);
		vo.setSuccess(Boolean.FALSE);
		vo.setData(data);
		return vo;
	}
}

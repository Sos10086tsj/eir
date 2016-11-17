package com.chinesedreamer.eir.exception.system;

import com.chinesedreamer.eir.exception.BaseException;
import com.chinesedreamer.eir.exception.ErrorMessage;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public class SessionOverdueException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8958508391027555510L;

	public SessionOverdueException(String message, String code) {
		super(message, code);
	}

	public SessionOverdueException(ErrorMessage errorMessage) {
		super(errorMessage);
	}
}

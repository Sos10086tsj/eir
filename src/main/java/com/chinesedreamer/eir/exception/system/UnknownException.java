package com.chinesedreamer.eir.exception.system;

import com.chinesedreamer.eir.exception.BaseException;
import com.chinesedreamer.eir.exception.ErrorMessage;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public class UnknownException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 789996373860648663L;

	public UnknownException(String message, String code) {
		super(message, code);
	}

	public UnknownException(ErrorMessage errorMessage) {
		super(errorMessage);
	}
}

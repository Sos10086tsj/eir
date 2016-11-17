package com.chinesedreamer.eir.exception.user;

import com.chinesedreamer.eir.exception.ErrorMessage;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 16, 2016
**/
public class UserRegisterFailureException extends UserException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2125563189876849630L;

	public UserRegisterFailureException(ErrorMessage errorMessage) {
		super(errorMessage);
	}

}

package com.chinesedreamer.eir.exception.user;

import com.chinesedreamer.eir.exception.ErrorMessage;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 16, 2016
**/
public class UsernameNotExistException extends UserException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3321005184631515771L;
	
	public UsernameNotExistException(ErrorMessage errorMessage) {
		super(errorMessage);
	}

}

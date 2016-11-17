package com.chinesedreamer.eir.service;

import com.chinesedreamer.eir.exception.user.UserException;
import com.chinesedreamer.eir.vo.model.user.UserPassVo;
import com.chinesedreamer.eir.vo.model.user.UserProfileVo;
import com.chinesedreamer.eir.vo.model.user.UserRegisterVo;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 15, 2016
**/
public interface UserService {
	/**
	 * 获取用户基本信息
	 * @param username
	 * @return
	 */
	public UserProfileVo getUser(String username);
	
	/**
	 * 更新用户基本信息
	 * @param vo
	 */
	public void updateProfile(UserProfileVo vo);
	
	/**
	 * 修改密码
	 * @param vo
	 * @throws UserException
	 */
	public void updatePassword(UserPassVo vo) throws UserException;
	
	/**
	 * 提交修改密码申请
	 * @param username
	 * @param email
	 * @throws UserException
	 */
	public void update2ResetPassword(String username, String email) throws UserException;
	
	/**
	 * 重置密码并激活
	 * @param token
	 * @return
	 * @throws UserException
	 */
	public void update2RetrievePassword(String token) throws UserException;
	
	/**
	 * 用户注册登记
	 * @param vo
	 * @throws UserException
	 */
	public void saveRegister(UserRegisterVo vo) throws UserException;
	
	public void login(String username, String password) throws UserException;
}

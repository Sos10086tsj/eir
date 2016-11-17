package com.chinesedreamer.eir.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinesedreamer.eir.vo.model.user.UserProfileVo;
import com.chinesedreamer.eir.vo.model.user.UserPassVo;
import com.chinesedreamer.eir.vo.response.ResponseVo;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 15, 2016
**/
@Controller
@RequestMapping(value="user")
public class UserController {

	private UserController(){}
	
	/**
	 * 进入用户基本资料编辑页面
	 * @return
	 */
	@RequestMapping(value = "profile", method = RequestMethod.GET)
	public String profile(){
		return "";
	}
	
	/**
	 * 获取用户基本资料
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "profile/data", method = RequestMethod.GET)
	public UserProfileVo getUserProfile(String username){
		return null;
	}
	
	/**
	 * 更新用户基本信息
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "profile/update", method = RequestMethod.POST)
	public ResponseVo updateProfile(UserProfileVo vo){
		return null;
	}
	
	/**
	 * 修改密码
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "password/update", method = RequestMethod.POST)
	public ResponseVo updatePassword(UserPassVo vo){
		return null;
	}
	
	/**
	 * 提交重置密码请求，只需要提供用户名或者邮箱其中一个
	 * @param username
	 * @param email
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "password/reset", method = RequestMethod.POST)
	public ResponseVo resetPassword(String username, String email){
		return null;
	}
	
	/**
	 * 根据token重置密码
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "password/retrieve", method = RequestMethod.GET)
	public String retrievePassword(String token){
		return "";
	}
}

package com.chinesedreamer.eir.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinesedreamer.eir.service.UserService;
import com.chinesedreamer.eir.vo.model.user.UserRegisterVo;
import com.chinesedreamer.eir.vo.response.ResponseVo;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 15, 2016
**/
@Controller
public class IndexController {
	
	private Logger logger = LoggerFactory.getLogger("EIR");
	
	@Resource
	private UserService userService;
	/**
	 * 进入注册页面
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(){
		return "";
	}
	
	/**
	 * 提交注册登记
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "register/save", method = RequestMethod.POST)
	public ResponseVo saveRegister(UserRegisterVo vo){
		return null;
	}
	
	/**
	 * 系统首页
	 * @return
	 */
	@RequestMapping(value = {"","login"} , method = RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	/**
	 * 用户登陆
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "login" , method = RequestMethod.POST)
	public String submitLogin(String username, String password, Model model, HttpServletRequest request){
		try {
			this.userService.login(username, password);
			return "redirect:/index";
		} catch (Exception e) {
			this.logger.error("{}",e);
			model.addAttribute("errorMessage", "用户名或者密码错误");
			return "login";
		}
	}
	
	/**
	 * 进入系统首页
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(){
		return "index";
	}
}

package com.chinesedreamer.eir.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import com.chinesedreamer.eir.base.BaseTest;
import com.chinesedreamer.eir.service.UserService;
import com.chinesedreamer.eir.vo.model.user.UserRegisterVo;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public class UserServiceImplTest extends BaseTest{
	
	@Resource
	private UserService userService;

	@Test
	public void testSaveRegister() {
		UserRegisterVo vo = new UserRegisterVo();
		vo.setName("测试用户");
		vo.setPassword("test");
		vo.setUsername("test");
		this.userService.saveRegister(vo);
	}

}

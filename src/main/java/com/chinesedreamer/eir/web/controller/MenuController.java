package com.chinesedreamer.eir.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinesedreamer.eir.service.MenuService;
import com.chinesedreamer.eir.util.ResponseUtil;
import com.chinesedreamer.eir.vo.response.ResponseVo;
import com.chinesedreamer.eir.vo.system.MenuVo;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 18, 2016
**/
@Controller
public class MenuController {
	
	@Resource
	private MenuService menuService;
	/**
	 * 获取用户菜单
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="menu")
	public ResponseVo menu(){
		List<MenuVo> menuVos = this.menuService.getUserMenus();
		return ResponseUtil.success(menuVos);
	}
}

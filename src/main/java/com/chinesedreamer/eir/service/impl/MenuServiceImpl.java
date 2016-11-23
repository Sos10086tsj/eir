package com.chinesedreamer.eir.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.eir.domain.dao.MenuDao;
import com.chinesedreamer.eir.domain.model.Menu;
import com.chinesedreamer.eir.service.MenuService;
import com.chinesedreamer.eir.util.SessionUtil;
import com.chinesedreamer.eir.vo.system.MenuVo;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 18, 2016
**/
@Service
public class MenuServiceImpl implements MenuService{

	@Resource
	private MenuDao dao;
	
	@Override
	public List<MenuVo> getUserMenus() {
		List<MenuVo> vos = new ArrayList<MenuVo>();
		List<Menu> menus = this.dao.findUserMenus(SessionUtil.getUserId());
		Map<Long, MenuVo> map = new HashMap<Long, MenuVo>();
		for (Menu menu : menus) {
			if (null == menu.getParentId()) {
				Long key = menu.getId();
				MenuVo vo = null;
				if (map.containsKey(key)) {
					vo = map.get(key);
				}else {
					vo = new MenuVo();
					vo.setSubMenus(new ArrayList<MenuVo>());
				}
				vo.setIconCss(menu.getIconCss());
				vo.setName(menu.getName());
				vo.setUrl(menu.getUrl());
				map.put(key, vo);
			}else {
				Long key = menu.getParentId();
				MenuVo vo = null;
				if (map.containsKey(key)) {
					vo = map.get(key);
				}else {
					vo = new MenuVo();
					vo.setSubMenus(new ArrayList<MenuVo>());
				}
				MenuVo subMenuVo = new MenuVo();
				subMenuVo.setIconCss(menu.getIconCss());
				subMenuVo.setName(menu.getName());
				subMenuVo.setUrl(menu.getUrl());
				
				vo.getSubMenus().add(subMenuVo);
				map.put(key, vo);
			}
		}
		
		for (Long key : map.keySet()) {
			vos.add(map.get(key));
		}
		return vos;
	}
}

package com.chinesedreamer.eir.domain.dao;

import java.util.List;

import com.chinesedreamer.eir.domain.model.Menu;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 18, 2016
**/
public interface MenuDao {
	public List<Menu> findUserMenus(Long userId);
}

package com.chinesedreamer.eir.domain.dao;

import com.chinesedreamer.eir.domain.model.User;

/**
 * Description: Auth:Paris Date:Nov 16, 2016
 **/
public interface UserDao {
	public User findByUsername(String username);
	public int save(User user);
	public int update(User user);
}

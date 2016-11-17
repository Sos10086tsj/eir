package com.chinesedreamer.eir.domain.model;
/**
 * Description:
 * Auth:Paris
 * Date:Nov 16, 2016
**/
public class User extends BaseModel{
	private String username;
	private String salt;
	private String password;
	private String name;
	public String getUsername() {
		return username;
	}
	public String getSalt() {
		return salt;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", salt=" + salt + ", password=" + password + ", name=" + name + ", id=" + id + "]";
	}
	
	
}

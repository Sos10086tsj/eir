package com.chinesedreamer.eir.vo.model.user;
/**
 * Description:
 * Auth:Paris
 * Date:Nov 15, 2016
**/
public class UserPassVo {
	private String username;
	private String oldPass;
	private String newPass;
	public String getUsername() {
		return username;
	}
	public String getOldPass() {
		return oldPass;
	}
	public String getNewPass() {
		return newPass;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	
	
}

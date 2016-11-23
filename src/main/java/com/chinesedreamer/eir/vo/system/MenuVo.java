package com.chinesedreamer.eir.vo.system;

import java.util.List;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 18, 2016
**/
public class MenuVo {
	private String name;
	private String iconCss;
	private String url;
	private List<MenuVo> subMenus;
	public String getName() {
		return name;
	}
	public String getIconCss() {
		return iconCss;
	}
	public String getUrl() {
		return url;
	}
	public List<MenuVo> getSubMenus() {
		return subMenus;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setIconCss(String iconCss) {
		this.iconCss = iconCss;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setSubMenus(List<MenuVo> subMenus) {
		this.subMenus = subMenus;
	}
}

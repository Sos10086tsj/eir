package com.chinesedreamer.eir.domain.model;
/**
 * Description:
 * Auth:Paris
 * Date:Nov 18, 2016
**/
public class Menu extends BaseModel{
	private String name;
	private String iconCss;
	private String url;
	private Integer hierarchy;
	private String sequence;
	private Long parentId;
	public String getName() {
		return name;
	}
	public String getIconCss() {
		return iconCss;
	}
	public String getUrl() {
		return url;
	}
	public Integer getHierarchy() {
		return hierarchy;
	}
	public String getSequence() {
		return sequence;
	}
	public Long getParentId() {
		return parentId;
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
	public void setHierarchy(Integer hierarchy) {
		this.hierarchy = hierarchy;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	
}

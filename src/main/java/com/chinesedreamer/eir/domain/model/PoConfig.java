package com.chinesedreamer.eir.domain.model;
/**
 * Description:
 * Auth:Paris
 * Date:Nov 25, 2016
**/
public class PoConfig extends BaseModel{
	private String type;
	private String category;
	private String propKey;
	private String propValue;
	private Integer sequence;
	public String getType() {
		return type;
	}
	public String getCategory() {
		return category;
	}
	public String getPropKey() {
		return propKey;
	}
	public String getPropValue() {
		return propValue;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setPropKey(String propKey) {
		this.propKey = propKey;
	}
	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	
}

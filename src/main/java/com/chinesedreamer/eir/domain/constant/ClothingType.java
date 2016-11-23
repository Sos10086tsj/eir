package com.chinesedreamer.eir.domain.constant;
/**
 * Description:
 * Auth:Paris
 * Date:Nov 23, 2016
**/
public enum ClothingType {
	MAN("MAN","男装"),
	WOMAN("WOMAN","女装"),
	BOY("BOY","男童"),
	GIRL("GIRL","女童"),
	PARENT_CHILD("PARENT_CHILD","亲子装");
	
	private final String type;
	private final String description;
	
	private ClothingType(String type,String description){
		this.type = type;
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}
}

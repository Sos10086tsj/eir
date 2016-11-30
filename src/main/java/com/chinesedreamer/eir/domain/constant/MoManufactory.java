package com.chinesedreamer.eir.domain.constant;
/**
 * Description:
 * Auth:Paris
 * Date:Nov 29, 2016
**/
public enum MoManufactory {
	PTM("PTM","普天姆"),
	JN("JN","佳楠");
	
	private final String code;
	private final String name;
	
	private MoManufactory(String code,String name){
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public static MoManufactory get(String code) {
		for (MoManufactory manufactory : MoManufactory.values()) {
			if (manufactory.getCode().equals(code)) {
				return manufactory;
			}
		}
		return null;
	}
}

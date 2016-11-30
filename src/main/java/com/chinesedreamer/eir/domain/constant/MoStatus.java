package com.chinesedreamer.eir.domain.constant;
/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public enum MoStatus {
	WAITING_4_PARSE(0,"等待处理"),
	COMPLETED(2,"处理完成"),
	FAILED(3,"处理失败"),
	IGNORE(4,"不处理");
	
	private final Integer status;
	private final String description;

	private MoStatus(Integer status,String description){
		this.status = status;
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}
	
	
	public String getDescription() {
		return description;
	}

	public static MoStatus get(Integer value) {
		for (MoStatus status : MoStatus.values()) {
			if (status.getStatus().equals(value)) {
				return status;
			}
		}
		return MoStatus.IGNORE;
	}
}

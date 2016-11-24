package com.chinesedreamer.eir.domain.constant;
/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public enum PoStatus {
	WAITING_4_PARSE(0,"等待处理"),
	PARSING(1,"PO解析中"),
	TRANSFER_FINISHED(2,"PO解析中"),
	COMPLETED(3,"处理完成"),
	FAILED(4,"处理失败"),
	IGNORE(5,"不处理");
	
	private final Integer status;
	private final String description;

	private PoStatus(Integer status,String description){
		this.status = status;
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}
	
	
	public String getDescription() {
		return description;
	}

	public static PoStatus get(Integer value) {
		for (PoStatus status : PoStatus.values()) {
			if (status.getStatus().equals(value)) {
				return status;
			}
		}
		return PoStatus.IGNORE;
	}
}

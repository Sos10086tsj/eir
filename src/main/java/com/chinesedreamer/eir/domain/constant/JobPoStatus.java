package com.chinesedreamer.eir.domain.constant;
/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public enum JobPoStatus {
	WAITING_4_PARSE(0),
	PARSING(1),
	COMPLETED(2),
	FAILED(3),
	IGNORE(4);
	
	private final Integer status;

	private JobPoStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}
	
	public static JobPoStatus get(Integer value) {
		for (JobPoStatus status : JobPoStatus.values()) {
			if (status.getStatus().equals(value)) {
				return status;
			}
		}
		return JobPoStatus.IGNORE;
	}
}

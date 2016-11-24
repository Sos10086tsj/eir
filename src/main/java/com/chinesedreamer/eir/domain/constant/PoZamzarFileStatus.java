package com.chinesedreamer.eir.domain.constant;
/**
 * Description:
 * Auth:Paris
 * Date:Nov 24, 2016
**/
public enum PoZamzarFileStatus {
	WAITING_4_DOWNLOAD(0),
	DOWNLOADED(1),
	DOWNLOADED_WITH_ERROR(100),
	DEFAULT(-1);
	private final Integer status;
	private PoZamzarFileStatus(Integer status){
		this.status = status;
	}
	public Integer getStatus() {
		return status;
	}
	
	public static PoZamzarFileStatus get(Integer value) {
		for (PoZamzarFileStatus status : PoZamzarFileStatus.values()) {
			if (status.getStatus().equals(value)) {
				return status;
			}
		}
		return PoZamzarFileStatus.DEFAULT;
	}
}

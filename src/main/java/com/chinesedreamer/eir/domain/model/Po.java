package com.chinesedreamer.eir.domain.model;

import java.util.Date;

import com.chinesedreamer.eir.domain.constant.ClothingType;
import com.chinesedreamer.eir.domain.constant.PoStatus;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 23, 2016
**/
public class Po extends BaseModel{

	private Long poFileId;
	private String poFileName;
	private String poFilePath;
	private PoStatus status = PoStatus.WAITING_4_PARSE;
	private ClothingType clothingType; 
	
	private Date createDate = new Date();
	private Long createUser;
	public Long getPoFileId() {
		return poFileId;
	}
	public PoStatus getStatus() {
		return status;
	}
	public ClothingType getClothingType() {
		return clothingType;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public Long getCreateUser() {
		return createUser;
	}
	public void setPoFileId(Long poFileId) {
		this.poFileId = poFileId;
	}
	public void setStatus(PoStatus status) {
		this.status = status;
	}
	public void setClothingType(ClothingType clothingType) {
		this.clothingType = clothingType;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	public String getPoFileName() {
		return poFileName;
	}
	public void setPoFileName(String poFileName) {
		this.poFileName = poFileName;
	}
	public String getPoFilePath() {
		return poFilePath;
	}
	public void setPoFilePath(String poFilePath) {
		this.poFilePath = poFilePath;
	}
	
	
}

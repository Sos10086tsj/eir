package com.chinesedreamer.eir.domain.model;

import java.util.Date;

import com.chinesedreamer.eir.domain.constant.ClothingType;
import com.chinesedreamer.eir.domain.constant.MoManufactory;
import com.chinesedreamer.eir.domain.constant.MoStatus;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 29, 2016
**/
public class Mo extends BaseModel{
	private Long moFileId;
	private ClothingType clothingType;
	private MoManufactory manufactory;
	private MoStatus status = MoStatus.WAITING_4_PARSE;
	private Date createDate = new Date();
	private Long createUser;
	public Long getMoFileId() {
		return moFileId;
	}
	public ClothingType getClothingType() {
		return clothingType;
	}
	public MoManufactory getManufactory() {
		return manufactory;
	}
	public MoStatus getStatus() {
		return status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public Long getCreateUser() {
		return createUser;
	}
	public void setMoFileId(Long moFileId) {
		this.moFileId = moFileId;
	}
	public void setClothingType(ClothingType clothingType) {
		this.clothingType = clothingType;
	}
	public void setManufactory(MoManufactory manufactory) {
		this.manufactory = manufactory;
	}
	public void setStatus(MoStatus status) {
		this.status = status;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	
	
}

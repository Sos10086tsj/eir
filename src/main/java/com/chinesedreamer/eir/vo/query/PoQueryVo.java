package com.chinesedreamer.eir.vo.query;
/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public class PoQueryVo extends PageVo{
	private Long poId;
	private String orderNo;
	private String styleNo;
	private Long createUser;
	
	public Long getPoId() {
		return poId;
	}

	public void setPoId(Long poId) {
		this.poId = poId;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public String getStyleNo() {
		return styleNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}
	
}

package com.chinesedreamer.eir.vo.model.po;

import java.util.Map;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 28, 2016
**/
public class PoDetailItemVo {
	private String orderNo;
	private String styleNo;
	private Map<String, Map<String,Integer>> items;
	public String getOrderNo() {
		return orderNo;
	}
	public String getStyleNo() {
		return styleNo;
	}
	public Map<String, Map<String, Integer>> getItems() {
		return items;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}
	public void setItems(Map<String, Map<String, Integer>> items) {
		this.items = items;
	}
	
	
}

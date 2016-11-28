package com.chinesedreamer.eir.vo.model.po;

import java.util.List;

import com.chinesedreamer.eir.vo.KeyValueVo;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 28, 2016
**/
public class PoDetailVo {
	private List<KeyValueVo> sizes;
	private List<PoDetailItemVo> items;
	public List<KeyValueVo> getSizes() {
		return sizes;
	}
	public List<PoDetailItemVo> getItems() {
		return items;
	}
	public void setSizes(List<KeyValueVo> sizes) {
		this.sizes = sizes;
	}
	public void setItems(List<PoDetailItemVo> items) {
		this.items = items;
	}

	
}

package com.chinesedreamer.eir.vo.query;
/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public class PageVo {
	protected Integer pageNum = 1;
	protected Integer pageSize = 20;
	public Integer getPageNum() {
		return pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}

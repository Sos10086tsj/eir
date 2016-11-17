package com.chinesedreamer.eir.vo.query;

import java.util.List;

/**
 * Description:
 * Auth:Paris
 * Date:May 4, 2016
**/
public class Pagination<T extends Object>{
	
	private Integer pageNum;
	private Integer pageSize;
	private Long total;
	private Integer totalPage;
	private List<T> data;
	public Integer getPageNum() {
		return pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public Long getTotal() {
		return total;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public List<T> getData() {
		return data;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Pagination [pageNum=" + pageNum + ", pageSize=" + pageSize + ", total=" + total + ", totalPage="
				+ totalPage + ", data=" + data + "]";
	}
	
	
}

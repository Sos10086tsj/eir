package com.chinesedreamer.eir.vo.excel;
/**
 * Description:
 * Auth:Paris
 * Date:Dec 1, 2016
**/
public class JnExcel {
	private Integer startRow;
	private Integer endRow;
	private Integer sizeRow;
	private Integer sizeStartColumn;
	private Integer sizeEndColumn;
	private Integer itemStartRow;
	private Integer itemEndRow;
	
	public JnExcel(Integer startRow,Integer endRow,Integer sizeRow,Integer sizeStartColumn,Integer sizeEndColumn,Integer itemStartRow,Integer itemEndRow){
		this.endRow = endRow;
		this.itemEndRow = itemEndRow;
		this.itemStartRow = itemStartRow;
		this.sizeEndColumn = sizeEndColumn;
		this.sizeRow = sizeRow;
		this.sizeStartColumn = sizeStartColumn;
		this.startRow = startRow;
	}
	
	public Integer getStartRow() {
		return startRow;
	}
	public Integer getEndRow() {
		return endRow;
	}
	public Integer getSizeRow() {
		return sizeRow;
	}
	public Integer getSizeStartColumn() {
		return sizeStartColumn;
	}
	public Integer getSizeEndColumn() {
		return sizeEndColumn;
	}
	public Integer getItemStartRow() {
		return itemStartRow;
	}
	public Integer getItemEndRow() {
		return itemEndRow;
	}
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}
	public void setSizeRow(Integer sizeRow) {
		this.sizeRow = sizeRow;
	}
	public void setSizeStartColumn(Integer sizeStartColumn) {
		this.sizeStartColumn = sizeStartColumn;
	}
	public void setSizeEndColumn(Integer sizeEndColumn) {
		this.sizeEndColumn = sizeEndColumn;
	}
	public void setItemStartRow(Integer itemStartRow) {
		this.itemStartRow = itemStartRow;
	}
	public void setItemEndRow(Integer itemEndRow) {
		this.itemEndRow = itemEndRow;
	}

	@Override
	public String toString() {
		return "JnExcel [startRow=" + startRow + ", endRow=" + endRow + ", sizeRow=" + sizeRow + ", sizeStartColumn="
				+ sizeStartColumn + ", sizeEndColumn=" + sizeEndColumn + ", itemStartRow=" + itemStartRow
				+ ", itemEndRow=" + itemEndRow + "]";
	}
	
	
}

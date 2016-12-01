package com.chinesedreamer.eir.vo.excel;
/**
 * Description:
 * Auth:Paris
 * Date:Dec 1, 2016
**/
public class ExcelCellMerge {
	private Integer startRow;
	private Integer endRow;
	private Integer startColumn;
	private Integer endColumn;
	
	public ExcelCellMerge(Integer startRow,Integer endRow,Integer startColumn,Integer endColumn){
		this.endColumn = endColumn;
		this.endRow = endRow;
		this.startColumn = startColumn;
		this.startRow = startRow;
	}
	
	public Integer getStartRow() {
		return startRow;
	}
	public Integer getEndRow() {
		return endRow;
	}
	public Integer getStartColumn() {
		return startColumn;
	}
	public Integer getEndColumn() {
		return endColumn;
	}
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}
	public void setStartColumn(Integer startColumn) {
		this.startColumn = startColumn;
	}
	public void setEndColumn(Integer endColumn) {
		this.endColumn = endColumn;
	}
	
	
}

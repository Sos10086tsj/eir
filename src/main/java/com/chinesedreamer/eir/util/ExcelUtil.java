package com.chinesedreamer.eir.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import com.chinesedreamer.eir.vo.excel.ExcelCellMerge;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 25, 2016
**/
public class ExcelUtil {
	public static String getCellStringValue(Cell cell) {
		if (null == cell) {
			return null;
		}
		String val = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			boolean value = cell.getBooleanCellValue();
			val = String.valueOf(value);
			break;
		case Cell.CELL_TYPE_ERROR:
			byte b = cell.getErrorCellValue();
			val = String.valueOf(b);
			break;
		case Cell.CELL_TYPE_FORMULA:
			val = cell.getCellFormula();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			val = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			val = cell.getStringCellValue();
			break;
		default:
			break;
		}
		if (StringUtils.isNotEmpty(val)) {
			val = val.trim();
		}
		return val;
	}
	
	public static String getCellStringValue(Cell cell, boolean removeZero) {
		String val = getCellStringValue(cell);
		if (removeZero && StringUtils.isNotEmpty(val)) {
			if (val.indexOf(".") != -1) {
				val = val.replaceAll("0+?$", "");
				val = val.replaceAll("[.]$", "");
			}
		}
		return val;
	}
	
	public static Integer getCellIntValue(Cell cell) {
		String val = getCellStringValue(cell,true);
		if (StringUtils.isNotEmpty(val)) {
			return Integer.valueOf(val);
		}else {
			return null;
		}
	}
	
	public static ExcelCellMerge getMergeInfo(Sheet sheet, Integer startRow, Integer startColumn) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (firstRow == startRow.intValue() && firstColumn == startColumn.intValue()) {
				return new ExcelCellMerge(firstRow, lastRow, firstColumn, lastColumn);
			}
		}
		return null;
	}
}

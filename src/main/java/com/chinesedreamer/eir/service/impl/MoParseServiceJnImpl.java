package com.chinesedreamer.eir.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinesedreamer.eir.constant.ApplicationConstant;
import com.chinesedreamer.eir.constant.JnExcelConstant;
import com.chinesedreamer.eir.domain.dao.MoItemCpqDao;
import com.chinesedreamer.eir.domain.dao.PoConfigDao;
import com.chinesedreamer.eir.domain.model.Mo;
import com.chinesedreamer.eir.domain.model.MoItemCpq;
import com.chinesedreamer.eir.service.MoParseService;
import com.chinesedreamer.eir.util.ExcelUtil;
import com.chinesedreamer.eir.util.PropertiesUtil;
import com.chinesedreamer.eir.util.StringUtil;
import com.chinesedreamer.eir.vo.excel.ExcelCellMerge;
import com.chinesedreamer.eir.vo.excel.JnExcel;

/**
 * Description: CPQ 佳楠解析
 * Auth:Paris
 * Date:Nov 30, 2016
**/
@Service("jnMoParseService")
public class MoParseServiceJnImpl implements MoParseService{
	private Logger logger = LoggerFactory.getLogger("EIR");
	
	@Resource
	private MoItemCpqDao moItemCpqDao;
	@Resource
	private PoConfigDao poConfigDao;

	@Override
	public void saveMoItems(Mo mo) {
		PropertiesUtil pu = new PropertiesUtil(ApplicationConstant.APPLICATION_PROPERTY_FILE);
		File excel = new File(pu.getProperty(ApplicationConstant.PROPERTY_FILE_UPLOAD_ROOT_KEY) + mo.getMoFilePath());
		List<MoItemCpq> items = new ArrayList<MoItemCpq>();
		Set<Long> deleteIds = new HashSet<Long>();
		if (excel.getName().endsWith(".xls")) {
			this.parse03Excel(mo,excel,items,deleteIds);
		}else if (excel.getName().endsWith(".xlsx")) {
			this.parse07Excel(excel,items,deleteIds);
		}
		for (MoItemCpq item : items) {
			this.moItemCpqDao.save(item);
		}
		if (!deleteIds.isEmpty()) {
			this.moItemCpqDao.deleteInBatch(new ArrayList<Long>(deleteIds));
		}
	}

	private void parse03Excel(Mo mo,File excel,List<MoItemCpq> items,Set<Long> deleteIds) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(excel));
			//佳楠分 荷兰/香港  01 两种表格
			String config = this.poConfigDao.findByTypeAndCategoryAndKey(ApplicationConstant.PO_CONFIG_TYPE_MO_PARSE, ApplicationConstant.PO_CONFIG_CATEGORY_MO, ApplicationConstant.MO_CONFIG_TYPE_JN).getPropValue();
			JSONObject moConfig = JSON.parseObject(config);
			List<String> sheetNames = new ArrayList<String>();
			if (StringUtils.isNotEmpty(moConfig.getString("hk"))) {
				for (String string : moConfig.getString("hk").split(ApplicationConstant.PO_CONFIG_DELIMITER)) {
					sheetNames.add(string);
				}
			}
			if (StringUtils.isNotEmpty(moConfig.getString("netherlands"))) {
				for (String string : moConfig.getString("netherlands").split(ApplicationConstant.PO_CONFIG_DELIMITER)) {
					sheetNames.add(string);
				}
			}
			if (StringUtils.isNotEmpty(moConfig.getString("01"))) {
				for (String string : moConfig.getString("01").split(ApplicationConstant.PO_CONFIG_DELIMITER)) {
					sheetNames.add(string);
				}
			}
			for (String sheetName : sheetNames) {
				HSSFSheet sheet = workbook.getSheet(sheetName);
				if (null == sheet) {
					continue;
				}
				List<JnExcel> jnSheetItems = new ArrayList<JnExcel>();
				Integer startRow = null;
				Integer endRow = null;
				Integer sizeRow = null;
				Integer sizeStartColumn = null;
				Integer sizeEndColumn = null;
				Integer itemStartRow = null;
				Integer itemEndRow = null;
				for (int i = 0; i < sheet.getLastRowNum(); i++) {
					HSSFRow row = sheet.getRow(i);
					if (null == row) {
						continue;
					}
					HSSFCell cell = row.getCell(JnExcelConstant.checkColumn);
					if (null == cell) {
						continue;
					}
					String cellValue = ExcelUtil.getCellStringValue(cell);
					if (StringUtil.matchKeyWord(cellValue, moConfig.getString("order"))) {
						startRow = i;
					}
					if (StringUtil.matchKeyWord(cellValue, moConfig.getString("itemRowBegin"))) {
						itemStartRow = i+2;
						sizeRow = i+1;
						for (int j = 1; j < row.getLastCellNum(); j++) {
							HSSFCell sizeCell = row.getCell(j);
							if (null != sizeCell) {
								String sizeCellValue = ExcelUtil.getCellStringValue(sizeCell);
								if (StringUtil.matchKeyWord(sizeCellValue, moConfig.getString("size"))) {
									ExcelCellMerge sizeCellMerge = ExcelUtil.getMergeInfo(sheet, i, j);
									sizeStartColumn = j;
									sizeEndColumn = sizeCellMerge.getEndColumn();
									break;
								}
							}
						}
					}
					if (StringUtil.matchKeyWord(cellValue, moConfig.getString("itemRowEnd"))) {
						itemEndRow = i - 1;
						endRow = i;
						JnExcel itemExcel = new JnExcel(startRow, endRow, sizeRow, sizeStartColumn, sizeEndColumn, itemStartRow, itemEndRow);
						jnSheetItems.add(itemExcel);
						startRow = null;
						endRow = null;
						sizeRow = null;
						sizeStartColumn = null;
						sizeEndColumn = null;
						itemStartRow = null;
						itemEndRow = null;
					}
				}
				for (JnExcel sheetItem : jnSheetItems) {
					Integer from = 0;
					Integer to = 0;
					String color = "";
					String country = "";
					String orderNo = "";
					String styleNo = "";
					
					//orderno, style no
					HSSFRow orderRow = sheet.getRow(sheetItem.getStartRow());
					for (int i = 0; i < orderRow.getLastCellNum(); i++) {
						HSSFCell orderRowCell = orderRow.getCell(i);
						String orderRowCellValue = ExcelUtil.getCellStringValue(orderRowCell);
						if (StringUtil.matchKeyWord(orderRowCellValue, moConfig.getString("order"))) {
							HSSFRow countryRow = sheet.getRow(sheetItem.getStartRow() + 1);
							for (int j = 0; j < countryRow.getLastCellNum(); j++) {
								HSSFCell countryCell = countryRow.getCell(j);
								String countryCellValue = ExcelUtil.getCellStringValue(countryCell);
								if (StringUtils.isNotEmpty(countryCellValue) && countryCellValue.contains(moConfig.getString("country"))) {
									for (String countryReplace : moConfig.getString("countryReplace").split(ApplicationConstant.PO_CONFIG_DELIMITER)) {
										countryCellValue = countryCellValue.replaceAll(countryReplace, "");
									}
									country = countryCellValue;
									break;
								}
							}
							for (int j = i+1; j < orderRow.getLastCellNum(); j++) {
								HSSFCell orderCell = orderRow.getCell(j);
								String orderCellValue = ExcelUtil.getCellStringValue(orderCell);
								if (StringUtils.isNotEmpty(orderCellValue)) {
									orderNo = orderCellValue;
									i = j+1;
									break;
								}
							}
						}
						if (StringUtil.matchKeyWord(orderRowCellValue, moConfig.getString("style"))) {
							for (int j = i+1; j < orderRow.getLastCellNum(); j++) {
								HSSFCell styleCell = orderRow.getCell(j);
								String styleCellValue = ExcelUtil.getCellStringValue(styleCell);
								if (StringUtils.isNotEmpty(styleCellValue)) {
									styleNo = styleCellValue;
									i = j+1;
									break;
								}
							}
						}
					}
					
					// size
					for (int i = sheetItem.getItemStartRow(); i <= sheetItem.getItemEndRow(); i++) {
						MoItemCpq itemCpq = new MoItemCpq();
						itemCpq.setMoId(mo.getId());
						itemCpq.setOrderNo(orderNo);
						itemCpq.setStyleNo(styleNo);
						itemCpq.setCountry(country);
						
						HSSFRow boxRow = sheet.getRow(i);
						HSSFCell boxCell = boxRow.getCell(JnExcelConstant.boxColumn);
						if (null != boxCell) {
							String boxStr = ExcelUtil.getCellStringValue(boxCell);
							if (StringUtils.isNotEmpty(boxStr)) {
								String[] boxStrs = boxStr.split("-");
								from = Integer.valueOf(boxStrs[0]);
								if (boxStrs.length == 2) {
									to = Integer.valueOf(boxStrs[1]);
								}else {
									to = from;
								}
							}
						}
						itemCpq.setBoxFrom(from);
						itemCpq.setBoxTo(to);
						
						HSSFCell colorCell = boxRow.getCell(JnExcelConstant.colorColumn);
						if (null != colorCell) {
							String colorStr = ExcelUtil.getCellStringValue(colorCell);
							if (StringUtils.isNotEmpty(colorStr)) {
								int colorIndex = colorStr.indexOf("-");
								if (colorIndex != -1) {
									colorStr = colorStr.substring(0, colorIndex);
								}
								color = colorStr;
							}
						}
						itemCpq.setColor(color);
						
						HSSFRow itemSizeRow = sheet.getRow(sheetItem.getSizeRow());
						for (int j = sheetItem.getSizeStartColumn(); j <= sheetItem.getSizeEndColumn(); j++) {
							HSSFCell colorSizeCell = boxRow.getCell(j);
							if (null != colorSizeCell) {
								Integer colorSizeValue = ExcelUtil.getCellIntValue(colorSizeCell);
								String itemSize = ExcelUtil.getCellStringValue(itemSizeRow.getCell(j)).toLowerCase().replaceAll("-", "").replaceAll("_", "");
								if (itemSize.equals("s")) {
									itemCpq.setSizeS(colorSizeValue);
								}else if (itemSize.equals("m")) {
									itemCpq.setSizeM(colorSizeValue);
								}else if (itemSize.equals("l")) {
									itemCpq.setSizeL(colorSizeValue);
								}else if (itemSize.equals("xl")) {
									itemCpq.setSizeXl(colorSizeValue);
								}else if (itemSize.equals("xxl")) {
									itemCpq.setSizeXxl(colorSizeValue);
								}else if (itemSize.equals("p")) {
									itemCpq.setSizeP(colorSizeValue);
								}else if (itemSize.equals("1")) {
									itemCpq.setSize1(colorSizeValue);
								}else if (itemSize.equals("2")) {
									itemCpq.setSize2(colorSizeValue);
								}else if (itemSize.equals("3")) {
									itemCpq.setSize3(colorSizeValue);
								}else if (itemSize.equals("4")) {
									itemCpq.setSize4(colorSizeValue);
								}else if (itemSize.equals("6")) {
									itemCpq.setSize6(colorSizeValue);
								}else if (itemSize.equals("8")) {
									itemCpq.setSize8(colorSizeValue);
								}else if (itemSize.equals("10")) {
									itemCpq.setSize10(colorSizeValue);
								}else if (itemSize.equals("12")) {
									itemCpq.setSize12(colorSizeValue);
								}else if (itemSize.equals("14")) {
									itemCpq.setSize14(colorSizeValue);
								}else if (itemSize.equals("16")) {
									itemCpq.setSize16(colorSizeValue);
								}else if (itemSize.equals("uni1")) {
									itemCpq.setSizeUni1(colorSizeValue);
								}else if (itemSize.equals("uni2")) {
									itemCpq.setSizeUni2(colorSizeValue);
								}else if (itemSize.equals("uni3")) {
									itemCpq.setSizeUni3(colorSizeValue);
								}else if (itemSize.equals("uni4")) {
									itemCpq.setSizeUni4(colorSizeValue);
								}else if (itemSize.equals("uni5")) {
									itemCpq.setSizeUni5(colorSizeValue);
								}else if (itemSize.equals("uni6")) {
									itemCpq.setSizeUni6(colorSizeValue);
								}
							}
						}
						items.add(itemCpq);
						List<MoItemCpq> exists = this.moItemCpqDao.findByOrderNo(orderNo);
						for (MoItemCpq exist : exists) {
							deleteIds.add(exist.getId());
						}
					}
				}
			}
			
			workbook.close();
		} catch (Exception e) {
			this.logger.error("{}",e);
		}
	}
	
	private void parse07Excel(File excel,List<MoItemCpq> items,Set<Long> deleteIds) {
	}
}

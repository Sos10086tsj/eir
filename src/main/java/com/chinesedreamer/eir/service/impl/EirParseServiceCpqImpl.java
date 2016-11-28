package com.chinesedreamer.eir.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.eir.constant.ApplicationConstant;
import com.chinesedreamer.eir.domain.dao.PoConfigDao;
import com.chinesedreamer.eir.domain.dao.PoItemCpqDao;
import com.chinesedreamer.eir.domain.model.PoConfig;
import com.chinesedreamer.eir.domain.model.PoItemCpq;
import com.chinesedreamer.eir.service.EirParseService;
import com.chinesedreamer.eir.util.ExcelUtil;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 25, 2016
**/
@Service("cpqEirParseService")
public class EirParseServiceCpqImpl implements EirParseService{

	private Logger logger = LoggerFactory.getLogger("EIR");
	@Resource
	private PoConfigDao poConfigDao;
	@Resource
	private PoItemCpqDao poItemCpqDao;
	
	@Override
	public void savePoExcel(Long poId,String filePath) {
		List<PoItemCpq> poItems = this.readPo(poId,filePath);
		for (PoItemCpq item : poItems) {
			if (null == item.getId()) {
				this.poItemCpqDao.save(item);
			}else {
				this.poItemCpqDao.update(item);
			}
		}
	}
	
	private List<PoItemCpq> readPo(Long poId,String filePath) {
		List<PoItemCpq> poItems = new ArrayList<PoItemCpq>();
		try {
			List<PoConfig> poParseConfigs = this.poConfigDao.findByTypeAndCategory(ApplicationConstant.PO_CONFIG_TYPE_PO_PARSE, ApplicationConstant.PO_CONFIG_CATEGORY_CPQ_PO);
			Map<String, String> configMap = new HashMap<String, String>();
			for (PoConfig poConfig : poParseConfigs) {
				configMap.put(poConfig.getPropKey(), poConfig.getPropValue());
			}
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(filePath);
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				String orderNo = null;
				String newStyleNo = null;
				String oldStyleNo = null;
				boolean itemStart = false;
				List<Integer> itemRows = new ArrayList<Integer>();
				for (int j = 0; j < sheet.getLastRowNum(); j++) {
					XSSFRow row = sheet.getRow(j);
					for (int k = 0; k < row.getLastCellNum(); k++) {
						XSSFCell cell = row.getCell(k);
						if (null == cell) {
							continue;
						}
						String cellValue = ExcelUtil.getCellStringValue(cell);
						if (StringUtils.isEmpty(cellValue)) {
							continue;
						}
						if (cellValue.replaceAll(" ", "").equalsIgnoreCase(configMap.get(ApplicationConstant.PO_CONFIG_CPQ_PO_KEY_ORDER).replaceAll(" ", ""))) {//开始获取order no
							for (int l = k+1; l < row.getLastCellNum(); l++) {
								XSSFCell orderCell = row.getCell(l);
								String orderCellValue = ExcelUtil.getCellStringValue(orderCell);
								if (StringUtils.isNotEmpty(orderCellValue)) {
									orderNo = orderCellValue;
									break;
								}
							}
						}else if (cellValue.replaceAll(" ", "").equalsIgnoreCase(configMap.get(ApplicationConstant.PO_CONFIG_CPQ_PO_KEY_NEW_STYLE).replaceAll(" ", ""))) {
							for (int l = k+1; l < row.getLastCellNum(); l++) {
								XSSFCell newStyleCell = row.getCell(l);
								String newStyleCellValue = ExcelUtil.getCellStringValue(newStyleCell,true);
								if (StringUtils.isNotEmpty(newStyleCellValue)) {
									newStyleNo = newStyleCellValue;
									break;
								}
							}
						}else if (cellValue.replaceAll(" ", "").equalsIgnoreCase(configMap.get(ApplicationConstant.PO_CONFIG_CPQ_PO_KEY_OLD_STYLE).replaceAll(" ", ""))) {
							for (int l = k+1; l < row.getLastCellNum(); l++) {
								XSSFCell oldStyleCell = row.getCell(l);
								String oldStyleCellValue = ExcelUtil.getCellStringValue(oldStyleCell,true);
								if (StringUtils.isNotEmpty(oldStyleCellValue)) {
									oldStyleNo = oldStyleCellValue;
									break;
								}
							}
						}else if (cellValue.replaceAll(" ", "").equalsIgnoreCase(configMap.get(ApplicationConstant.PO_CONFIG_CPQ_PO_KEY_ITEM_BEGIN).replaceAll(" ", ""))) {
							itemStart = true;
							itemRows.add(j + 1);
						}
						if (itemStart && k == 0) {
							Cell sizeCell = row.getCell(0);
							String sizeCellValue = ExcelUtil.getCellStringValue(sizeCell);
							if (StringUtils.isNotEmpty(sizeCellValue) && sizeCellValue.toLowerCase().startsWith("size")) {
								itemRows.add(j);
							}
						}
					}
				}
				//处理item数据
				XSSFRow colorRow = sheet.getRow(itemRows.get(0));
				for (int k = 1; k < colorRow.getLastCellNum() - 1; k++) {
					String colorCellValue = ExcelUtil.getCellStringValue(colorRow.getCell(k));
					int index = colorCellValue.indexOf("-");
					if (index != -1) {
						colorCellValue = colorCellValue.substring(0, index);
					}
					PoItemCpq item = this.poItemCpqDao.findByOrderAndStyleAndColor(orderNo, oldStyleNo, colorCellValue);
					if (null == item) {
						item = new PoItemCpq();
						item.setOrderNo(orderNo);
						item.setNewStyleNo(newStyleNo);
						item.setOldStyleNo(oldStyleNo);
						item.setColor(colorCellValue);
						
					}
					item.setPoId(poId);
					for (int l = 1; l < itemRows.size(); l++) {
						XSSFRow sizeRow = sheet.getRow(itemRows.get(l));
						XSSFCell sizeCell = sizeRow.getCell(0);
						String sizeCellValue = ExcelUtil.getCellStringValue(sizeCell).replaceAll(" ", "").replaceAll("-", "").toLowerCase();
						
						XSSFCell itemCell = sizeRow.getCell(k);
						Integer itemCellValue = ExcelUtil.getCellIntValue(itemCell);
						if (sizeCellValue.equals("sizes")) {
							item.setSizeS(itemCellValue);
						}else if (sizeCellValue.equals("sizem")) {
							item.setSizeM(itemCellValue);
						}else if (sizeCellValue.equals("sizel")) {
							item.setSizeL(itemCellValue);
						}else if (sizeCellValue.equals("sizexl")) {
							item.setSizeXl(itemCellValue);
						}else if (sizeCellValue.equals("sizexxl")) {
							item.setSizeXxl(itemCellValue);
						}else if (sizeCellValue.equals("sizep")) {
							item.setSizeP(itemCellValue);
						}else if (sizeCellValue.equals("size1")) {
							item.setSize1(itemCellValue);
						}else if (sizeCellValue.equals("size2")) {
							item.setSize2(itemCellValue);
						}else if (sizeCellValue.equals("size3")) {
							item.setSize3(itemCellValue);
						}else if (sizeCellValue.equals("size4")) {
							item.setSize4(itemCellValue);
						}else if (sizeCellValue.equals("size6")) {
							item.setSize6(itemCellValue);
						}else if (sizeCellValue.equals("size8")) {
							item.setSize8(itemCellValue);
						}else if (sizeCellValue.equals("size10")) {
							item.setSize10(itemCellValue);
						}else if (sizeCellValue.equals("size12")) {
							item.setSize12(itemCellValue);
						}else if (sizeCellValue.equals("size14")) {
							item.setSize14(itemCellValue);
						}else if (sizeCellValue.equals("size16")) {
							item.setSize16(itemCellValue);
						}else if (sizeCellValue.equals("sizeuni1")) {
							item.setSizeUni1(itemCellValue);
						}else if (sizeCellValue.equals("sizeuni2")) {
							item.setSizeUni2(itemCellValue);
						}else if (sizeCellValue.equals("sizeuni3")) {
							item.setSizeUni3(itemCellValue);
						}else if (sizeCellValue.equals("sizeuni4")) {
							item.setSizeUni4(itemCellValue);
						}else if (sizeCellValue.equals("sizeuni5")) {
							item.setSizeUni5(itemCellValue);
						}else if (sizeCellValue.equals("sizeuni6")) {
							item.setSizeUni6(itemCellValue);
						}
					}
					poItems.add(item);
				}
			}
//			workbook.close();
		} catch (Exception e) {
			this.logger.error("{}",e);
		}
		return poItems;
	}
}

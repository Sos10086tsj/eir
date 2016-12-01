package com.chinesedreamer.eir.task.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import com.chinesedreamer.eir.base.BaseTest;
import com.chinesedreamer.eir.task.service.MoExcelParseService;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 30, 2016
**/
public class MoExcelParseServiceImplTest extends BaseTest{
	@Resource
	private MoExcelParseService moExcelParseService;

	@Test
	public void testSaveExcel2Model() {
		this.moExcelParseService.saveExcel2Model();
	}

}

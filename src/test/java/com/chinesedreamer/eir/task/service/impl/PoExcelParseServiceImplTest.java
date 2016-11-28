package com.chinesedreamer.eir.task.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.chinesedreamer.eir.base.BaseTest;
import com.chinesedreamer.eir.task.service.PoExcelParseService;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 28, 2016
**/
public class PoExcelParseServiceImplTest extends BaseTest{
	@Resource
	private PoExcelParseService poExcelParseService;
	@Test
	@Rollback(value=false)
	public void testSaveExcel2Model() {
		this.poExcelParseService.saveExcel2Model();
	}

}

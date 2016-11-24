package com.chinesedreamer.eir.task.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import com.chinesedreamer.eir.base.BaseTest;
import com.chinesedreamer.eir.task.service.PoParseService;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 24, 2016
**/
public class PoParseServiceImplTest extends BaseTest{

	@Resource
	private PoParseService poParseService;
	@Test
	public void testUpdate2ParsePo() {
		this.poParseService.update2ParsePo();
	}

}

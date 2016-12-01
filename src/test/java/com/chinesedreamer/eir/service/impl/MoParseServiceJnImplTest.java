package com.chinesedreamer.eir.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.chinesedreamer.eir.base.BaseTest;
import com.chinesedreamer.eir.domain.model.Mo;
import com.chinesedreamer.eir.service.MoParseService;

/**
 * Description:
 * Auth:Paris
 * Date:Dec 1, 2016
**/
public class MoParseServiceJnImplTest extends BaseTest{

	@Resource(name="jnMoParseService")
	private MoParseService jnMoParseService;
	
	@Test
	@Rollback(value=false)
	public void testSaveMoItems() {
		Mo mo = new Mo();
		mo.setMoFilePath("2016/11/30/5d8d1c1e-0530-4fa9-bbbf-5e73257b90e0.xls");
		this.jnMoParseService.saveMoItems(mo);
	}

}

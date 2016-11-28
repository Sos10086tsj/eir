package com.chinesedreamer.eir.service.impl;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.chinesedreamer.eir.base.BaseTest;
import com.chinesedreamer.eir.service.PoService;
import com.chinesedreamer.eir.vo.model.po.PoDetailVo;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 28, 2016
**/
public class PoServiceImplTest extends BaseTest{
	
	@Resource
	private PoService poService;

	@Test
	public void testFindPoDetail() {
		PoDetailVo vo = this.poService.findPoDetail(3l);
		Assert.assertNotNull(vo);
		System.out.println(vo);
	}

}

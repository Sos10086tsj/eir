package com.chinesedreamer.eir.task.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import com.chinesedreamer.eir.base.BaseTest;
import com.chinesedreamer.eir.task.service.PoZamzarFileService;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 25, 2016
**/
public class PoZamzarFileServiceImplTest extends BaseTest{

	@Resource
	private PoZamzarFileService poZamzarFileService;
	@Test
	public void testUpdate2DownloadFile() {
		this.poZamzarFileService.update2DownloadFile();
	}

}

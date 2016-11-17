package com.chinesedreamer.eir.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import com.chinesedreamer.eir.base.BaseTest;
import com.chinesedreamer.eir.service.JobService;
import com.chinesedreamer.eir.vo.model.job.JobVo;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public class JobServiceImplTest extends BaseTest{
	@Resource
	private JobService jobService;
	
	@Test
	public void testSave() {
		JobVo vo = new JobVo();
		vo.setName("测试job创建");
		this.jobService.save(vo);
	}

}

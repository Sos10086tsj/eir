package com.chinesedreamer.eir.web.controller;

import java.io.FileInputStream;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;

import com.chinesedreamer.eir.base.BaseTest;
import com.chinesedreamer.eir.vo.response.ResponseVo;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public class EirControllerTest extends BaseTest{
	
	@Autowired
	private EirController eirController;

	@Test
	public void testJobPoUpload() {
		try {
			MockMultipartFile po = new MockMultipartFile("tx.png","tx.png",null, new FileInputStream("C:/Users/Paris/Desktop/tx.png"));
			eirController.jobPoUpload(2l, po);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testJobPo(){
		ResponseVo vo = this.eirController.jobPo(2l);
		Assert.assertNotNull(vo);
		System.out.println("vo:" + vo);
	}
}

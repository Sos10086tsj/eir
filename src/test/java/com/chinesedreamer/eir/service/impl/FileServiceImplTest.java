package com.chinesedreamer.eir.service.impl;

import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import com.chinesedreamer.eir.base.BaseTest;
import com.chinesedreamer.eir.service.FileService;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public class FileServiceImplTest extends BaseTest{
	
	@Resource
	private FileService fileService;

	@Test
	public void testUpdate2UploadFile() {
		try {
			MockMultipartFile mockMultipartFile = new MockMultipartFile("tx.png","tx.png",null, new FileInputStream("C:/Users/Paris/Desktop/tx.png"));
			Long fileId = this.fileService.update2UploadFile(mockMultipartFile);
			assertNotNull(fileId);
			System.out.println("fileId:" + fileId);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

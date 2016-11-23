package com.chinesedreamer.eir.service.impl;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chinesedreamer.eir.constant.ApplicationConstant;
import com.chinesedreamer.eir.domain.dao.EirFileDao;
import com.chinesedreamer.eir.domain.model.EirFile;
import com.chinesedreamer.eir.service.FileService;
import com.chinesedreamer.eir.util.PropertiesUtil;
import com.chinesedreamer.eir.util.SessionUtil;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
@Service
public class FileServiceImpl implements FileService{
	private Logger logger = LoggerFactory.getLogger("EIR");
	
	@Resource
	private EirFileDao dao;
	
	@Override
	public EirFile save(MultipartFile file) {
		PropertiesUtil pu = new PropertiesUtil(ApplicationConstant.APPLICATION_PROPERTY_FILE);
		Calendar calendar = Calendar.getInstance();
		String fileFoler = pu.getProperty(ApplicationConstant.PROPERTY_FILE_UPLOAD_ROOT_KEY)
				+ calendar.get(Calendar.YEAR) + File.separator
				+ (calendar.get(Calendar.MONTH) + 1) + File.separator
				+ calendar.get(Calendar.DAY_OF_MONTH) + File.separator;
		File folder = new File(fileFoler);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		StringBuilder builder = new StringBuilder();
		builder.append(UUID.randomUUID().toString());
		int typeIndex = file.getOriginalFilename().lastIndexOf(".");
		if (typeIndex != -1) {
			builder.append(".")
			.append(file.getOriginalFilename().substring(typeIndex + 1));
		}
		String fileName = builder.toString();
		File saveFile = new File(fileFoler + fileName);
		try {
			file.transferTo(saveFile);
			
		} catch (Exception e) {
			this.logger.error("{}", e);
			return null;
		}
		
		EirFile eirFile = new EirFile();
		eirFile.setFileName(file.getOriginalFilename());
		eirFile.setFilePath(fileFoler + fileName);
		eirFile.setUploadDate(new Date());
		eirFile.setUploadUser(SessionUtil.getUserId());
		this.dao.save(eirFile);
		return eirFile;
	}

}

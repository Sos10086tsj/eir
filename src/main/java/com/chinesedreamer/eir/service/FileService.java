package com.chinesedreamer.eir.service;

import org.springframework.web.multipart.MultipartFile;

import com.chinesedreamer.eir.domain.model.EirFile;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public interface FileService {
	public EirFile save(MultipartFile file);
}

package com.chinesedreamer.eir.tools.zamazar.service;

import java.io.File;

import com.chinesedreamer.eir.tools.zamazar.model.ZamzarResponse;
import com.chinesedreamer.eir.tools.zamazar.model.ZamzarStatus;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 24, 2016
**/
public interface ZamzarService {
	
	/**
	 * 解析文件
	 * @param sourceFile
	 * @param targetFormat
	 * @return
	 */
	public ZamzarResponse zamzarFile(String sourceFile, String targetFormat);
	
	/**
	 * 文件下载
	 * @param fileId
	 * @return
	 */
	public File download(String fileId);
	
	/**
	 * 检查文件状态
	 * @param fileId
	 * @return
	 */
	public ZamzarStatus zamzarStatus(String fileId);
}

package com.chinesedreamer.eir.tools.zamazar.service;

import com.chinesedreamer.eir.domain.model.EirFile;
import com.chinesedreamer.eir.tools.zamazar.model.ZamzarResponse;

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
	public EirFile saveDownloadFile(String fileId,String fileName);
	
	/**
	 * 检查文件状态
	 * @param jobId
	 * @return
	 */
	public ZamzarResponse zamzarStatus(String jobId);
}

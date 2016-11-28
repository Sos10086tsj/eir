package com.chinesedreamer.eir.task.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinesedreamer.eir.domain.dao.PoZamzarFileDao;
import com.chinesedreamer.eir.domain.dao.PoZamzarHistoryDao;
import com.chinesedreamer.eir.domain.model.EirFile;
import com.chinesedreamer.eir.domain.model.PoZamzarFile;
import com.chinesedreamer.eir.domain.model.PoZamzarHistory;
import com.chinesedreamer.eir.task.service.PoZamzarFileService;
import com.chinesedreamer.eir.tools.zamazar.model.ZamzarResponse;
import com.chinesedreamer.eir.tools.zamazar.service.ZamzarService;
import com.github.pagehelper.PageHelper;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 24, 2016
**/
@Service
public class PoZamzarFileServiceImpl implements PoZamzarFileService{
	private Logger logger = LoggerFactory.getLogger("EIR");
	@Resource
	private PoZamzarHistoryDao poZamzarHistoryDao;
	@Resource
	private ZamzarService zamzarService;
	@Resource
	private PoZamzarFileDao poZamzarFileDao;

	@Override
	@Scheduled(cron="0 0/5 * * * ?")
	public void update2DownloadFile() {
		//1. 检查文件状态
		PageHelper.startPage(1, 10);
		List<PoZamzarHistory> histories = this.poZamzarHistoryDao.findParsedPos();
		if (histories.isEmpty()) {
			return;
		}
		for (PoZamzarHistory history : histories) {
			String jobId = history.getJobId();
			ZamzarResponse response = this.zamzarService.zamzarStatus(jobId);
			if (response.getSuccess() && response.getStatus().equals("successful")) {
				JSONArray jsonArray = JSONArray.parseArray(response.getTargetFiles());
				int totalFile = jsonArray.size();
				int downloadedFile = 0;
				for (Object object : jsonArray) {
					JSONObject jsonObject = (JSONObject)object;
					String fileId = jsonObject.getString("id");
					//2. 下载文件
					//判断文件是否已经存在
					PoZamzarFile exist = this.poZamzarFileDao.findByZamzarFileId(fileId);
					if (null == exist) {
						EirFile file = this.zamzarService.saveDownloadFile(fileId);
						if (null != file.getId()) {
							PoZamzarFile poZamzarFile = new PoZamzarFile();
							poZamzarFile.setFileId(file.getId());
							poZamzarFile.setHistoryId(history.getId());
							poZamzarFile.setZamzarFileId(fileId);
							this.poZamzarFileDao.save(poZamzarFile);
							downloadedFile ++;
						}else {
							this.logger.info("Fail to download file. history id : {}, file id : {}.", history.getId(), fileId);
						}
					}else {
						downloadedFile ++;
					}
				}
				if (totalFile == downloadedFile) {
					this.poZamzarHistoryDao.updateFileDownloaded(history.getId());
				}
			}
		}
	}

}

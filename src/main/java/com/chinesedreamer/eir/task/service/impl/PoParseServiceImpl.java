package com.chinesedreamer.eir.task.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.chinesedreamer.eir.domain.constant.PoStatus;
import com.chinesedreamer.eir.domain.dao.PoDao;
import com.chinesedreamer.eir.domain.dao.PoZamzarHistoryDao;
import com.chinesedreamer.eir.domain.model.Po;
import com.chinesedreamer.eir.domain.model.PoZamzarHistory;
import com.chinesedreamer.eir.task.service.PoParseService;
import com.chinesedreamer.eir.tools.zamazar.model.ZamzarResponse;
import com.chinesedreamer.eir.tools.zamazar.service.ZamzarService;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 24, 2016
**/
@Service
public class PoParseServiceImpl implements PoParseService{
	private Logger logger = LoggerFactory.getLogger("EIR");

	@Resource
	private PoDao poDao;
	@Resource
	private ZamzarService zamzarService;
	@Resource
	private PoZamzarHistoryDao poZamzarHistoryDao;
	@Override
	@Scheduled(cron="0 0/5 * * * ?")
	public void update2ParsePo() {
		//1. 获取需要解析的po
		Po newPo = this.poDao.findNewPo();
		if (null == newPo) {
			return;
		}
		this.logger.info("Start parse po:{} to excel.", newPo.getId());
		//2. 发起zamzar请求
		this.poDao.updatePoStatus(newPo.getId(), PoStatus.PARSING.getStatus());
		try {
			ZamzarResponse response = this.zamzarService.zamzarFile(newPo.getPoFilePath(), "xlsx");
			//3. 记录反馈结果
			PoZamzarHistory history = new PoZamzarHistory();
			history.setPoId(newPo.getId());
			history.setResponseContent(JSON.toJSONString(response));
			history.setJobId(response.getId());
			if (!response.getSuccess()) {
				history.setErrorMessage(response.getErrors());
				history.setSuccess(Boolean.FALSE);
			}
			this.poZamzarHistoryDao.save(history);
		} catch (Exception e) {
			this.logger.error("{}",e);
			this.poDao.updatePoStatus(newPo.getId(), PoStatus.FAILED.getStatus());
		}
	}
}

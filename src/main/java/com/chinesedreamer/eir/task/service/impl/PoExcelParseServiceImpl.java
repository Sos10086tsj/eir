package com.chinesedreamer.eir.task.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.chinesedreamer.eir.constant.ApplicationConstant;
import com.chinesedreamer.eir.domain.constant.PoStatus;
import com.chinesedreamer.eir.domain.dao.PoDao;
import com.chinesedreamer.eir.domain.model.Po;
import com.chinesedreamer.eir.service.EirParseFactory;
import com.chinesedreamer.eir.task.service.PoExcelParseService;
import com.chinesedreamer.eir.util.PropertiesUtil;
import com.chinesedreamer.eir.util.SessionUtil;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 25, 2016
**/
@Service
public class PoExcelParseServiceImpl implements PoExcelParseService{

	@Resource
	private PoDao poDao;
	@Resource
	private EirParseFactory eirParseFactory;
	
	@Override
	@Scheduled(cron="0 0/5 * * * ?")
	public void saveExcel2Model() {
		List<Po> pos = this.poDao.findNewPoExcels();
		if (pos.isEmpty()) {
			return;
		}
		PropertiesUtil pu = new PropertiesUtil(ApplicationConstant.APPLICATION_PROPERTY_FILE);
		String rootPath = pu.getProperty(ApplicationConstant.PROPERTY_FILE_UPLOAD_ROOT_KEY);
		Long poId = null;
		for (Po po : pos) {
			if (null == poId) {
				poId = po.getId();
			}
			String filePath = rootPath + po.getPoFilePath();
			this.eirParseFactory.getInstant(SessionUtil.getEirType()).savePoExcel(poId, filePath);
		}
		this.poDao.updatePoStatus(poId, PoStatus.COMPLETED.getStatus());
	}

}

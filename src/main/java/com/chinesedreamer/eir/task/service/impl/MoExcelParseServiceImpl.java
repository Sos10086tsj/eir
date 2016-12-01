package com.chinesedreamer.eir.task.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.chinesedreamer.eir.domain.constant.MoStatus;
import com.chinesedreamer.eir.domain.dao.MoDao;
import com.chinesedreamer.eir.domain.model.Mo;
import com.chinesedreamer.eir.service.MoParseFactory;
import com.chinesedreamer.eir.task.service.MoExcelParseService;
import com.chinesedreamer.eir.util.SessionUtil;
import com.github.pagehelper.PageHelper;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 30, 2016
**/
@Service
public class MoExcelParseServiceImpl implements MoExcelParseService{
	
	@Resource
	private MoDao moDao;
	@Resource
	private MoParseFactory moParseFactory;

	@Override
	@Scheduled(cron="0 0/5 * * * ?")
	public void saveExcel2Model() {
		PageHelper.startPage(1, 10, false);
		List<Mo> mos = this.moDao.findWaitingMos();
		for (Mo mo : mos) {
			this.moParseFactory.getInstance(SessionUtil.getEirType(), mo.getManufactory()).saveMoItems(mo);
			this.moDao.updateStatus(mo.getId(), MoStatus.COMPLETED);
		}
	}

}

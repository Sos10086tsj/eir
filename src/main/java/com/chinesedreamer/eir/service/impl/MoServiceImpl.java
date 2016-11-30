package com.chinesedreamer.eir.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.eir.domain.constant.ClothingType;
import com.chinesedreamer.eir.domain.constant.MoManufactory;
import com.chinesedreamer.eir.domain.dao.MoDao;
import com.chinesedreamer.eir.domain.model.Mo;
import com.chinesedreamer.eir.service.MoService;
import com.chinesedreamer.eir.util.SessionUtil;
import com.chinesedreamer.eir.vo.model.mo.MoVo;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 29, 2016
**/
@Service
public class MoServiceImpl implements MoService{
	
	@Resource
	private MoDao moDao;

	@Override
	public void saveMo(MoVo vo) {
		Mo mo = new Mo();
		mo.setClothingType(ClothingType.valueOf(vo.getClothingType()));
		mo.setCreateUser(SessionUtil.getUserId());
		mo.setManufactory(MoManufactory.get(vo.getManufactory()));
		mo.setMoFileId(vo.getFileId());
		this.moDao.save(mo);
	}

}

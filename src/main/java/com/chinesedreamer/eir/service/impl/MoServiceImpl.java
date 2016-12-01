package com.chinesedreamer.eir.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.eir.domain.constant.ClothingType;
import com.chinesedreamer.eir.domain.constant.MoManufactory;
import com.chinesedreamer.eir.domain.dao.MoDao;
import com.chinesedreamer.eir.domain.model.Mo;
import com.chinesedreamer.eir.service.MoService;
import com.chinesedreamer.eir.util.SessionUtil;
import com.chinesedreamer.eir.vo.model.mo.MoVo;
import com.chinesedreamer.eir.vo.query.MoQueryVo;
import com.chinesedreamer.eir.vo.query.Pagination;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 29, 2016
**/
@Service
public class MoServiceImpl implements MoService{
	
	@Resource
	private MoDao dao;

	@Override
	public void saveMo(MoVo vo) {
		Mo mo = new Mo();
		mo.setClothingType(ClothingType.valueOf(vo.getClothingType()));
		mo.setCreateUser(SessionUtil.getUserId());
		mo.setManufactory(MoManufactory.get(vo.getManufactory()));
		mo.setMoFileId(vo.getFileId());
		this.dao.save(mo);
	}

	@Override
	public Pagination<MoVo> findMos(MoQueryVo queryVo) {
		PageHelper.startPage(queryVo.getPageNum(), queryVo.getPageSize());
		List<Mo> mos = this.dao.findMos(queryVo);
		Pagination<MoVo> vos = new Pagination<MoVo>();
		vos.setPageNum(queryVo.getPageNum());
		vos.setPageSize(queryVo.getPageSize());
		
		Page<Mo> page = (Page<Mo>)mos;
		vos.setTotal(page.getTotal());
		vos.setTotalPage(page.getPageSize());
		List<MoVo> moVos = new ArrayList<MoVo>();
		for (Mo mo : mos) {
			moVos.add(this.model2Vo(mo));
		}
		vos.setData(moVos);
		return vos;
	}

	private MoVo model2Vo(Mo mo) {
		MoVo vo = new MoVo();
		vo.setClothingType(mo.getClothingType().getDescription());
		vo.setFileName(mo.getMoFileName());
		vo.setFilePath(mo.getMoFilePath());
		vo.setId(mo.getId());
		vo.setManufactory(mo.getManufactory().getName());
		vo.setStatus(mo.getStatus().getDescription());
		vo.setUploadDate(mo.getCreateDate());
		return vo;
	}
}

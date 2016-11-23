package com.chinesedreamer.eir.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.eir.domain.constant.ClothingType;
import com.chinesedreamer.eir.domain.dao.PoDao;
import com.chinesedreamer.eir.domain.model.Po;
import com.chinesedreamer.eir.service.PoService;
import com.chinesedreamer.eir.util.SessionUtil;
import com.chinesedreamer.eir.vo.model.po.PoVo;
import com.chinesedreamer.eir.vo.query.Pagination;
import com.chinesedreamer.eir.vo.query.PoQueryVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 23, 2016
**/
@Service
public class PoServiceImpl implements PoService{

	@Resource
	private PoDao dao;
	
	@Override
	public void savePo(PoVo vo) {
		Po po = new Po();
		po.setClothingType(ClothingType.valueOf(vo.getClothingType()));
		po.setPoFileId(vo.getFileId());
		po.setCreateUser(SessionUtil.getUserId());
		this.dao.save(po);
	}

	@Override
	public Pagination<PoVo> findPage(PoQueryVo queryVo) {
		PageHelper.startPage(queryVo.getPageNum(), queryVo.getPageSize());
		List<Po> pos = this.dao.findPos(queryVo);
		Pagination<PoVo> vos = new Pagination<PoVo>();
		vos.setPageNum(queryVo.getPageNum());
		vos.setPageSize(queryVo.getPageSize());
		
		Page<Po> page = (Page<Po>)pos;
		vos.setTotal(page.getTotal());
		vos.setTotalPage(page.getPageSize());
		List<PoVo> poVos = new ArrayList<PoVo>();
		for (Po po : pos) {
			poVos.add(this.model2Vo(po));
		}
		vos.setData(poVos);
		return vos;
	}

	private PoVo model2Vo(Po po) {
		PoVo vo = new PoVo();
		vo.setClothingType(po.getClothingType().getDescription());
		vo.setFileId(po.getPoFileId());
		vo.setStatus(po.getStatus().getDescription());
		vo.setFileName(po.getPoFileName());
		vo.setFilePath(po.getPoFilePath());
		vo.setUploadDate(po.getCreateDate());
		return vo;
	}
}

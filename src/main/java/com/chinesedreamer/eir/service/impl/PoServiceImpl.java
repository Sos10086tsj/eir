package com.chinesedreamer.eir.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.eir.constant.ApplicationConstant;
import com.chinesedreamer.eir.domain.constant.ClothingType;
import com.chinesedreamer.eir.domain.dao.PoConfigDao;
import com.chinesedreamer.eir.domain.dao.PoDao;
import com.chinesedreamer.eir.domain.dao.PoItemCpqDao;
import com.chinesedreamer.eir.domain.model.Po;
import com.chinesedreamer.eir.domain.model.PoConfig;
import com.chinesedreamer.eir.domain.model.PoItemCpq;
import com.chinesedreamer.eir.service.PoService;
import com.chinesedreamer.eir.util.SessionUtil;
import com.chinesedreamer.eir.vo.KeyValueVo;
import com.chinesedreamer.eir.vo.model.po.PoDetailItemVo;
import com.chinesedreamer.eir.vo.model.po.PoDetailVo;
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
	@Resource
	private PoItemCpqDao poItemCpqDao;
	@Resource
	private PoConfigDao poConfigDao;
	
	@Override
	public void savePo(PoVo vo) {
		Po po = new Po();
		po.setClothingType(ClothingType.valueOf(vo.getClothingType()));
		po.setPoFileId(vo.getFileId());
		po.setCreateUser(SessionUtil.getUserId());
		this.dao.save(po);
	}

	@Override
	public Pagination<PoVo> findPos(PoQueryVo queryVo) {
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
		vo.setId(po.getId());
		vo.setClothingType(po.getClothingType().getDescription());
		vo.setFileId(po.getPoFileId());
		vo.setStatus(po.getStatus().getDescription());
		vo.setFileName(po.getPoFileName());
		vo.setFilePath(po.getPoFilePath());
		vo.setUploadDate(po.getCreateDate());
		return vo;
	}

	@Override
	public PoDetailVo findPoDetail(Long poId) {
		Po po = this.dao.findById(poId);
		List<PoConfig> sizesConfigs = this.poConfigDao.findByTypeAndCategory(ApplicationConstant.PO_CONFIG_TYPE_CLOTING_TYPE, po.getClothingType().getType());
		List<KeyValueVo> sizesVos = new ArrayList<KeyValueVo>();
		for (PoConfig sizesConfig : sizesConfigs) {
			KeyValueVo vo = new KeyValueVo();
			vo.setKey(sizesConfig.getPropValue().replaceAll(" ", "").replaceAll("-", "").toLowerCase());
			vo.setValue(sizesConfig.getPropValue());
			sizesVos.add(vo);
		}
		
		PoDetailVo vo = new PoDetailVo();
		vo.setSizes(sizesVos);
		vo.setItems(new ArrayList<PoDetailItemVo>());
		List<PoItemCpq> items = this.poItemCpqDao.findByPoId(poId);
		Map<String, List<PoItemCpq>> map = new HashMap<String, List<PoItemCpq>>();
		for (PoItemCpq item : items) {
			String key = item.getOrderNo();
			List<PoItemCpq> mapItem = null;
			if (map.containsKey(key)) {
				mapItem = map.get(key);
			}else {
				mapItem = new ArrayList<PoItemCpq>();
			}
			mapItem.add(item);
			map.put(key, mapItem);
		}
		
		for (String key : map.keySet()) {
			PoDetailItemVo detailItemVo = new PoDetailItemVo();
			detailItemVo.setOrderNo(key);
			detailItemVo.setStyleNo(map.get(key).get(0).getOldStyleNo());
			Map<String, Map<String,Integer>> detailSizeMap = new HashMap<String, Map<String,Integer>>();
			for (PoItemCpq item : map.get(key)) {
				for (KeyValueVo size : sizesVos) {
					String sizeKey = size.getKey();
					Integer sizeValue = null;
					switch (sizeKey) {
					case "sizes":
						sizeValue = item.getSizeS();
						break;
					case "sizem":
						sizeValue = item.getSizeM();
						break;
					case "sizel":
						sizeValue = item.getSizeL();
						break;
					case "sizexl":
						sizeValue = item.getSizeXl();
						break;
					case "sizexxl":
						sizeValue = item.getSizeXxl();
						break;
					case "sizep":
						sizeValue = item.getSizeP();
						break;
					case "size1":
						sizeValue = item.getSize1();
						break;
					case "size2":
						sizeValue = item.getSize2();
						break;
					case "size3":
						sizeValue = item.getSize3();
						break;
					case "size4":
						sizeValue = item.getSize4();
						break;
					case "size6":
						sizeValue = item.getSize6();
						break;
					case "size8":
						sizeValue = item.getSize8();
						break;
					case "size10":
						sizeValue = item.getSize10();
						break;
					case "size12":
						sizeValue = item.getSize12();
						break;
					case "size14":
						sizeValue = item.getSize14();
						break;
					case "size16":
						sizeValue = item.getSize16();
						break;
					case "sizeuni1":
						sizeValue = item.getSizeUni1();
						break;
					case "sizeuni2":
						sizeValue = item.getSizeUni2();
						break;
					case "sizeuni3":
						sizeValue = item.getSizeUni3();
						break;
					case "sizeuni4":
						sizeValue = item.getSizeUni4();
						break;
					case "sizeuni5":
						sizeValue = item.getSizeUni5();
						break;
					case "sizeuni6":
						sizeValue = item.getSizeUni6();
						break;
					default:
						break;
					}
					this.convertDetailMap(detailSizeMap, sizeKey, item.getColor(), sizeValue);
				}
			}
			detailItemVo.setItems(detailSizeMap);
			vo.getItems().add(detailItemVo);
		}
		return vo;
	}
	
	private void convertDetailMap(Map<String, Map<String,Integer>> detailSizeMap, String sizeKey, String color, Integer sizeValue) {
		Map<String,Integer> sizeMap = detailSizeMap.get(sizeKey);
		if (null == sizeMap) {
			sizeMap = new HashMap<String,Integer>();
		}
		sizeMap.put(color, sizeValue);
		detailSizeMap.put(sizeKey, sizeMap);
	}
}

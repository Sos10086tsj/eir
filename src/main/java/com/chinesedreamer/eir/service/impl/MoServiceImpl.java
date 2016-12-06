package com.chinesedreamer.eir.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.eir.constant.ApplicationConstant;
import com.chinesedreamer.eir.domain.constant.ClothingType;
import com.chinesedreamer.eir.domain.constant.MoManufactory;
import com.chinesedreamer.eir.domain.dao.MoDao;
import com.chinesedreamer.eir.domain.dao.MoItemCpqDao;
import com.chinesedreamer.eir.domain.dao.PoConfigDao;
import com.chinesedreamer.eir.domain.model.Mo;
import com.chinesedreamer.eir.domain.model.MoItemCpq;
import com.chinesedreamer.eir.domain.model.PoConfig;
import com.chinesedreamer.eir.service.MoService;
import com.chinesedreamer.eir.util.SessionUtil;
import com.chinesedreamer.eir.vo.KeyValueVo;
import com.chinesedreamer.eir.vo.model.mo.MoVo;
import com.chinesedreamer.eir.vo.model.po.PoDetailItemVo;
import com.chinesedreamer.eir.vo.model.po.PoDetailVo;
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
	@Resource
	private PoConfigDao poConfigDao;
	@Resource
	private MoItemCpqDao moItemCpqDao;

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

	@Override
	public PoDetailVo findPoDetail(Long moId) {
		Mo mo = this.dao.findById(moId);
		List<PoConfig> sizesConfigs = this.poConfigDao.findByTypeAndCategory(ApplicationConstant.PO_CONFIG_TYPE_CLOTING_TYPE, mo.getClothingType().getType());
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
		List<MoItemCpq> items = this.moItemCpqDao.findByMoId(moId);
		Map<String, List<MoItemCpq>> map = new HashMap<String, List<MoItemCpq>>();
		for (MoItemCpq item : items) {
			String key = item.getOrderNo();
			List<MoItemCpq> mapItem = null;
			if (map.containsKey(key)) {
				mapItem = map.get(key);
			}else {
				mapItem = new ArrayList<MoItemCpq>();
			}
			mapItem.add(item);
			map.put(key, mapItem);
		}
		
		for (String key : map.keySet()) {
			PoDetailItemVo detailItemVo = new PoDetailItemVo();
			detailItemVo.setOrderNo(key);
			detailItemVo.setStyleNo(map.get(key).get(0).getStyleNo());
			Map<String, Map<String,Integer>> detailSizeMap = new HashMap<String, Map<String,Integer>>();
			for (MoItemCpq item : map.get(key)) {
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

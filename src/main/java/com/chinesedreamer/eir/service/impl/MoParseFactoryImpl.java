package com.chinesedreamer.eir.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.eir.constant.EirType;
import com.chinesedreamer.eir.domain.constant.MoManufactory;
import com.chinesedreamer.eir.service.MoParseFactory;
import com.chinesedreamer.eir.service.MoParseService;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 30, 2016
**/
@Service
public class MoParseFactoryImpl implements MoParseFactory{
	
	@Resource(name="ptmMoParseService")
	private MoParseService ptmMoParseService;
	@Resource(name="jnMoParseService")
	private MoParseService jnMoParseService;

	@Override
	public MoParseService getInstance(EirType type, MoManufactory manufactory) {
		MoParseService service = null;
		switch (type) {
		case CPQ:
			switch (manufactory) {
			case PTM:
				service = ptmMoParseService;
				break;
			case JN:
				service = jnMoParseService;
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
		return service;
	}

}

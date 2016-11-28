package com.chinesedreamer.eir.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.eir.constant.EirType;
import com.chinesedreamer.eir.service.EirParseFactory;
import com.chinesedreamer.eir.service.EirParseService;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 25, 2016
**/
@Service
public class EirParseFactoryImpl implements EirParseFactory{

	@Resource(name="cpqEirParseService")
	private EirParseService cpqEirParseService;
	@Override
	public EirParseService getInstant(EirType type) {
		EirParseService instance = null;
		switch (type) {
		case CPQ:
			instance = cpqEirParseService;
			break;
		default:
			break;
		}
		return instance;
	}

}

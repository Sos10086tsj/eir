package com.chinesedreamer.eir.service;

import com.chinesedreamer.eir.constant.EirType;
import com.chinesedreamer.eir.domain.constant.MoManufactory;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 30, 2016
**/
public interface MoParseFactory {
	public MoParseService getInstance(EirType type,MoManufactory manufactory);
}

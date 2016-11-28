package com.chinesedreamer.eir.service;

import com.chinesedreamer.eir.constant.EirType;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 25, 2016
**/
public interface EirParseFactory {
	public EirParseService getInstant(EirType type);
}

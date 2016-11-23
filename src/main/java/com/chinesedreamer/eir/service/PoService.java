package com.chinesedreamer.eir.service;

import com.chinesedreamer.eir.vo.model.po.PoVo;
import com.chinesedreamer.eir.vo.query.Pagination;
import com.chinesedreamer.eir.vo.query.PoQueryVo;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 23, 2016
**/
public interface PoService {
	public void savePo(PoVo vo);
	public Pagination<PoVo> findPage(PoQueryVo queryVo);
}

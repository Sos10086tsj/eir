package com.chinesedreamer.eir.service;

import com.chinesedreamer.eir.vo.model.mo.MoVo;
import com.chinesedreamer.eir.vo.query.MoQueryVo;
import com.chinesedreamer.eir.vo.query.Pagination;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 29, 2016
**/
public interface MoService {
	public void saveMo(MoVo vo);
	public Pagination<MoVo> findMos(MoQueryVo queryVo);
}

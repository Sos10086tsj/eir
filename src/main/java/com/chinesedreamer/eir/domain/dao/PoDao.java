package com.chinesedreamer.eir.domain.dao;

import java.util.List;

import com.chinesedreamer.eir.domain.model.Po;
import com.chinesedreamer.eir.vo.query.PoQueryVo;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 23, 2016
**/
public interface PoDao {
	public int save(Po po);
	public List<Po> findPos(PoQueryVo queryVo);
}

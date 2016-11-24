package com.chinesedreamer.eir.domain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	
	/**
	 * 获取待解析的pdf po
	 * @return
	 */
	public Po findNewPo();
	public int updatePoStatus(@Param(value="id")Long id, @Param(value="status")Integer status);
}

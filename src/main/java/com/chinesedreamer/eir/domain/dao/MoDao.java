package com.chinesedreamer.eir.domain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chinesedreamer.eir.domain.constant.MoStatus;
import com.chinesedreamer.eir.domain.model.Mo;
import com.chinesedreamer.eir.vo.query.MoQueryVo;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 29, 2016
**/
public interface MoDao {
	public int save(Mo mo);
	public List<Mo> findMos(MoQueryVo queryVo);
	public List<Mo> findWaitingMos();
	public int updateStatus(@Param(value="id")Long id, @Param(value="status")MoStatus status);
	public Mo findById(Long moId);
}

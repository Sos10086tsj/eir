package com.chinesedreamer.eir.domain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chinesedreamer.eir.domain.model.PoConfig;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 25, 2016
**/
public interface PoConfigDao {
	public List<PoConfig> findByType(String type);
	public List<PoConfig> findByTypeAndCategory(@Param(value="type")String type,@Param(value="category")String category);
}

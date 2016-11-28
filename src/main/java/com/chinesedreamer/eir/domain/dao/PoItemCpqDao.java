package com.chinesedreamer.eir.domain.dao;

import org.apache.ibatis.annotations.Param;

import com.chinesedreamer.eir.domain.model.PoItemCpq;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 28, 2016
**/
public interface PoItemCpqDao {
	public int save(PoItemCpq item);
	public int update(PoItemCpq item);
	public PoItemCpq findByOrderAndStyleAndColor(@Param(value="orderNo")String orderNo, @Param(value="styleNo")String styleNo, @Param(value="color")String color);
}

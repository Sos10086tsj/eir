package com.chinesedreamer.eir.domain.dao;

import java.util.List;

import com.chinesedreamer.eir.domain.model.MoItemCpq;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 30, 2016
**/
public interface MoItemCpqDao {
	public int save(MoItemCpq item);
	public int deleteInBatch(List<Long> ids);
	public List<MoItemCpq> findByOrderNo(String orderNo);
	public List<MoItemCpq> findByMoId(Long moId);
}

package com.chinesedreamer.eir.domain.dao;

import java.util.List;

import com.chinesedreamer.eir.domain.model.PoZamzarHistory;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 24, 2016
**/
public interface PoZamzarHistoryDao {
	public int save(PoZamzarHistory history);
	/**
	 * 获取已经提交excel转换但是未下载的po列表
	 * @return
	 */
	public List<PoZamzarHistory> findParsedPos();
	
	public int updateFileDownloaded(Long id);
}

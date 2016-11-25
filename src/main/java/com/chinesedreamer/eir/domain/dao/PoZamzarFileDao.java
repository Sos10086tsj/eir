package com.chinesedreamer.eir.domain.dao;

import com.chinesedreamer.eir.domain.model.PoZamzarFile;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 24, 2016
**/
public interface PoZamzarFileDao {
	public int save(PoZamzarFile file);
	public PoZamzarFile findByZamzarFileId(String zamzarFileId);
}

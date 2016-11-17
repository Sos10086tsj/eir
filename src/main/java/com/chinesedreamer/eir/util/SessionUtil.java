package com.chinesedreamer.eir.util;

import java.util.concurrent.ConcurrentHashMap;

import com.chinesedreamer.eir.constant.ApplicationConstant;
import com.chinesedreamer.eir.web.filter.SessionFilter;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public class SessionUtil {
	
	public static String getSessionId() {
		try {
			ConcurrentHashMap<String, Object> map = SessionFilter.SessionContext.getContext();
			return (String)map.get(ApplicationConstant.SESSION_SESSIONID_KEY);
		} catch (Exception e) {
			return "TEST";
		}
		
	}
	
	public static Long getUserId() {
		try {
			ConcurrentHashMap<String, Object> map = SessionFilter.SessionContext.getContext();
			return (Long)map.get(ApplicationConstant.SESSION_USERID_KEY);
		} catch (Exception e) {
			return -1l;
		}
	}
}

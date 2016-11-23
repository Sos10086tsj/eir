package com.chinesedreamer.eir.util;

import javax.servlet.http.HttpServletRequest;

import com.chinesedreamer.eir.exception.ErrorMessageCode;
import com.chinesedreamer.eir.exception.system.SessionOverdueException;
import com.chinesedreamer.eir.web.filter.SessionFilter;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public class SessionUtil {
	
	public static String getSessionId() {
		HttpServletRequest request = SessionFilter.SessionContext.getContextRequest();
		if (null == request) {
			throw new SessionOverdueException(ErrorMessageCode.SYSTEM.SESSION_OVERDUE_MESSAGE, ErrorMessageCode.SYSTEM.SESSION_OVERDUE_CODE);
		}
		return request.getSession(false).getId();		
	}
	
	public static Long getUserId() {
		String sessionId = getSessionId();
		return SessionFilter.SessionContext.getContent().get(sessionId);
	}
	
	public static void addUserId(Long userId){
		SessionFilter.SessionContext.setContent(getSessionId(), userId);
	}
}

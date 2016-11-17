package com.chinesedreamer.eir.web.filter;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

import com.chinesedreamer.eir.exception.ErrorMessageCode;
import com.chinesedreamer.eir.exception.system.SessionOverdueException;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 17, 2016
**/
public class SessionFilter {

	
	public static class SessionContext implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = -2429316837819807679L;
		
		public static ThreadLocal<ConcurrentHashMap<String, Object>> threadLocal = new ThreadLocal<ConcurrentHashMap<String, Object>>();
		
		public static ConcurrentHashMap<String, Object> getContext(){
			if (null == threadLocal.get()) {
				throw new SessionOverdueException(ErrorMessageCode.SYSTEM.SESSION_OVERDUE_MESSAGE, ErrorMessageCode.SYSTEM.SESSION_OVERDUE_CODE);
			}
			return threadLocal.get();
		}
		
		public static void setContext(ConcurrentHashMap<String, Object> map){   
			threadLocal.set(map);
		}   
		
		public static void cleanContext(){   
			threadLocal.set(null);   
		}
	}
}

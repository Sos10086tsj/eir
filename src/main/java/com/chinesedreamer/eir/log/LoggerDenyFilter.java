package com.chinesedreamer.eir.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 11, 2016
**/
public class LoggerDenyFilter extends Filter<ILoggingEvent>{

	@Override
	public FilterReply decide(ILoggingEvent event) {
		if (event.getLoggerName().equals("EIR")){  
			return FilterReply.DENY;
        }else { 
            return FilterReply.ACCEPT;
        }
	}

}

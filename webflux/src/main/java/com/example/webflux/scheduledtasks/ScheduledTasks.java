package com.example.webflux.scheduledtasks;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.webflux.data.AccessLog;
import com.example.webflux.service.AccessLogManagerService;
import com.example.webflux.service.LoggingService;

@Component
public class ScheduledTasks {
	
	 
	 @Resource
	 LoggingService loggingService;
	 
	 @Resource
	 AccessLogManagerService logManagerService;

	    @Scheduled(fixedRate = 2000)
	    public void updateLog() {
	    	String loggedLine = loggingService.log();
	    	String[] arrLogLine = loggedLine.split(" ");
	    	
	    	try {
		    	AccessLog logLine = new AccessLog(arrLogLine[1],arrLogLine[5],arrLogLine[2]+" "+arrLogLine[3],new Date());
		    	logManagerService.saveLogEntry(logLine).subscribe();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	    }

}

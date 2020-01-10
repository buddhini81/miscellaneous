package com.example.webflux.service;


import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webflux.data.AccessLog;
import com.example.webflux.data.AccessLogRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccessLogManagerService {

	@Autowired
	private AccessLogRepository accessLogRepository;
	
	public Mono<AccessLog> saveLogEntry(AccessLog logEntry)  {
		try {
		return accessLogRepository.save(logEntry);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Flux<AccessLog> getAllAccessLogs() {
		Flux<AccessLog> logs = accessLogRepository.findWithTailableCursor().delayElements(Duration.ofMillis(2000));
		return logs;
	}
	
	public Mono<Long> getAccessCountByIP(String ip) {
		return accessLogRepository.findAccessCountByIP(ip);
	}
	
}

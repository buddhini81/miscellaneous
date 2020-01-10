/**
 * 
 */
package com.example.webflux.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.webflux.data.AccessLog;
import com.example.webflux.error.LogAccessException;
import com.example.webflux.service.AccessLogManagerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author buddhini
 *
 */

@Component
public class AccessLogHandler {

	@Autowired
	AccessLogManagerService logManagerService;

	public Mono<ServerResponse> handleGetAccessLog(ServerRequest serverRequest) {

		return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
				.body(getAccessLog(serverRequest)
						.onErrorResume(e -> Flux.error(new LogAccessException(HttpStatus.BAD_REQUEST,
								"User Access Log Stream cannot be obtained at this time!", e))),
						String.class);

	}

	private Flux<String> getAccessLog(ServerRequest serverRequest) {
		try {
			Flux<AccessLog> logs = logManagerService.getAllAccessLogs();

			Flux<String> results = logs.map(
					accesslog -> "User " + accesslog.getUser() + " " + accesslog.getAction() + " " + accesslog.getIp());

			return results;
		} catch (Exception e) {
			return Flux.error(e);
		}
	}
	

	public Mono<ServerResponse> handleGetAccessCountByIP(ServerRequest serverRequest) {

		return ServerResponse
				.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(
						getAccessCountByIP(serverRequest)
								.onErrorResume(e -> Mono.error(new LogAccessException(HttpStatus.BAD_REQUEST,
										"Access Log count for the given IP cannot be obtained at this time!", e))),
						Long.class);
	}

	private Mono<Long> getAccessCountByIP(ServerRequest serverRequest) {
		try {
			String ip = serverRequest.pathVariable("ip");

			Mono<Long> result = logManagerService.getAccessCountByIP(ip);

			return result;

		} catch (Exception e) {
			return Mono.error(e);
		}
	}

}

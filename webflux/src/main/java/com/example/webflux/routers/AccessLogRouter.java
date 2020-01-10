/**
 * 
 */
package com.example.webflux.routers;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.webflux.handlers.AccessLogHandler;

/**
 * @author buddhini
 *
 */
@Configuration
public class AccessLogRouter {
		
	
	@Bean
	public RouterFunction<ServerResponse> route(AccessLogHandler accessLogHandler) {
		
		 return RouterFunctions
                 .route(GET("/accesslog").and(accept(MediaType.TEXT_EVENT_STREAM)),accessLogHandler::handleGetAccessLog)
                 .andRoute(GET("/accesscount/{ip}").and(accept(MediaType.TEXT_EVENT_STREAM)),accessLogHandler::handleGetAccessCountByIP);
	}

}

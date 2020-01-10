/**
 * 
 */
package com.example.webflux.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author buddhini
 *
 */
public class LogAccessException extends ResponseStatusException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param status
	 */
	public LogAccessException(HttpStatus status, String message, Throwable e) {
		super(status, message, e);
	}

}

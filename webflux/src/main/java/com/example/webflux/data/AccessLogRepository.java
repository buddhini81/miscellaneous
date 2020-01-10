/**
 * 
 */
package com.example.webflux.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author buddhini
 *
 */

@Repository
public class AccessLogRepository {
	
	@Autowired
	ReactiveMongoTemplate reactiveMongoTemplate;
	
	@Tailable
    public Flux<AccessLog> findWithTailableCursor() {
		return reactiveMongoTemplate.findAll(AccessLog.class);
	}
	
	public Mono<AccessLog> save(AccessLog accessLog) {
		return reactiveMongoTemplate.save(accessLog);
	}
	
	public Mono<Long> findAccessCountByIP(String ip) {
		return reactiveMongoTemplate.count(new Query().addCriteria(Criteria.where("ip").is(ip)), AccessLog.class);
	}


}

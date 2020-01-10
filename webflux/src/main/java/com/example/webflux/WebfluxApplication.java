package com.example.webflux;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.webflux.service.LoggingService;


@SpringBootApplication
@EnableScheduling
public class WebfluxApplication implements CommandLineRunner {
	
	@Resource
	LoggingService loggingService;

	public static void main(String[] args) {
		SpringApplication.run(WebfluxApplication.class, args);
	}

	public void run(String...args) throws Exception {
		loggingService.deleteLogDirectoryAndFile();
		loggingService.initLog();
	}

}

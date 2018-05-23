package com.cefothe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MvcApplication {

	@Value("${environment.name}")
	private String environmentName;

	private static final Logger LOG = LoggerFactory.getLogger(MvcApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MvcApplication.class, args);
	}

	@PostConstruct
	public void postConstruct(){
		LOG.info("BG Judge initialized. Environment = {}", environmentName);
	}
}

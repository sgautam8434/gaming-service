package com.intuit.gaming_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class GamingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamingServiceApplication.class, args);
	}

}

package com.example.zidio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {
		"com.example.zidio",
		"com.example.controller",
		"com.example.DTO",
		"com.example.entity",
		"com.example.enums",
		"com.example.exception",
		"com.example.repository",
		"com.example.security",
		"com.example.service"
})
@EnableJpaRepositories(basePackages = "com.example.repository")
@EntityScan(basePackages = "com.example.entity")
@EnableScheduling

public class ZidioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZidioApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

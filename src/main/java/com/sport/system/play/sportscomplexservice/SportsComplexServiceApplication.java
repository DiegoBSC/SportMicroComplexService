package com.sport.system.play.sportscomplexservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SportsComplexServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsComplexServiceApplication.class, args);
	}

}

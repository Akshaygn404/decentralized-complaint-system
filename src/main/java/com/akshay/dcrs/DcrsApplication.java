package com.akshay.dcrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DcrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DcrsApplication.class, args);
	}

}

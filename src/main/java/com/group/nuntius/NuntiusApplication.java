package com.group.nuntius;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NuntiusApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.location", "target/application.properties");
		SpringApplication.run(NuntiusApplication.class, args);
	}

}

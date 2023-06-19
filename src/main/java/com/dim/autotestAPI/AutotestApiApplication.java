package com.dim.autotestAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@SpringBootApplication
public class AutotestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutotestApiApplication.class, args);
	}

}

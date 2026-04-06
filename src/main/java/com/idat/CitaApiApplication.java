package com.idat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.idat.model")
public class CitaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitaApiApplication.class, args);
	}

}

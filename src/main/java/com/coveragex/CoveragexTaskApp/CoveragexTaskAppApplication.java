package com.coveragex.CoveragexTaskApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class CoveragexTaskAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoveragexTaskAppApplication.class, args);
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime);
	}

}

package com.issuemoa.apm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class ApmApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApmApplication.class, args);
	}
}

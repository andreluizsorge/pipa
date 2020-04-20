package br.com.pipa.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IntegrationServiceMain {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "pipa-integration-api-config");
		SpringApplication app = new SpringApplication(IntegrationServiceMain.class);
		app.run(args);
	}
}

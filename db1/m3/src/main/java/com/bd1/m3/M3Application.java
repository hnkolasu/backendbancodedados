package com.bd1.m3;

import com.bd1.m3.config.DatabaseConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class M3Application {

	public static void main(String[] args) {
		DatabaseConfig databaseConfig = new DatabaseConfig();
		SpringApplication.run(M3Application.class, args);
		databaseConfig.configureDatabase();
	}
}

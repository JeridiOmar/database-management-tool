package com.example.databasemanagementtool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DatabaseManagementToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseManagementToolApplication.class, args);
	}

}

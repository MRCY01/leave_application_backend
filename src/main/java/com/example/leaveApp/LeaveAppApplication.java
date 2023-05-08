package com.example.leaveApp;

import com.example.leaveApp.repo.EmployeeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class LeaveAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaveAppApplication.class, args);
	}

}

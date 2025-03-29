package com.example.PatientManagementWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PatientManagementWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientManagementWebApplication.class, args);
	}

}

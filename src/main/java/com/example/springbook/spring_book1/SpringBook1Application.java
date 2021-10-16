package com.example.springbook.spring_book1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBook1Application {

	public static void main(String[] args) {

		SpringApplication.run(SpringBook1Application.class, args);
	}

}

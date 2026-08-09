package com.lisa.studentmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(escapeCharacter = '*')
@EntityScan(basePackages = "com.lisa.myspringbootwithjpa.model")
//@EnableTransactionManagement
public class MySpringBootWithJPA {

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootWithJPA.class, args);
	}

}

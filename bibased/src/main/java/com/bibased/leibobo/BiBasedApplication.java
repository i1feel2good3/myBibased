package com.bibased.leibobo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackageClasses = BiBasedApplication.class)
@SpringBootApplication
public class BiBasedApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiBasedApplication.class, args);
	}
}

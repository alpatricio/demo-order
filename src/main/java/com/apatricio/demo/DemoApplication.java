package com.apatricio.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EntityScan("com.apatricio.demo")
@EnableJpaRepositories("com.apatricio.demo")
@EnableSpringDataWebSupport
@SpringBootApplication(scanBasePackages = "com.apatricio.demo")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

package com.airefresco.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		AirefrescoApplication.class,
		Jsr310JpaConverters.class
})
public class AirefrescoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirefrescoApplication.class, args);
	}

}

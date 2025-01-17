package com.taggle.taggleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.taggle.taggleapi", "config"})
public class TaggleapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaggleapiApplication.class, args);
	}

}

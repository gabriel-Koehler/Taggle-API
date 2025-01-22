package com.taggle.taggleapi;

import org.modelmapper.internal.bytebuddy.asm.Advice.Return;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.taggle.taggleapi", "config"})
public class TaggleapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaggleapiApplication.class, args);
	}
// 	//TODO 
// Error Handling 

// Return appropriate HTTP status codes
// Provide clear error messages
// Include enough information for debugging without exposing sensitive details
// Use 204 for successful empty responses

// Documentation
// Use OpenAPI (formerly Swagger) for API documentation
// Document:
// Endpoint structure
// Request/response formats
// Authentication requirements
// Error codes and messages
}

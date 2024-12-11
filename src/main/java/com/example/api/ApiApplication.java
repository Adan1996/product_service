package com.example.api;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/products").allowedOrigins("*").allowedMethods("GET", "POST", "PUT");
				registry.addMapping("/api/supplier").allowedOrigins("*").allowedMethods("GET", "POST", "PUT");
				registry.addMapping("/api/products/{id}").allowedOrigins("*").allowedMethods("GET", "DELETE");
				registry.addMapping("/api/supplier/{id}").allowedOrigins("*").allowedMethods("GET", "DELETE");
			}
		};
	}
}

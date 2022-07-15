package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.microsoft.applicationinsights.attach.ApplicationInsights;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		ApplicationInsights.attach();
		SpringApplication.run(App.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
			}
		};
	}

	@Bean
	public ToDoRepository getToDoRepository() {
		// if cosmosdb connection string is present, return CosmosDBToDoRepository
		// else return InMemoryToDoRepository

		return new InMemoryToDoRepository();
	}

}

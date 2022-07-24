package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import com.microsoft.applicationinsights.attach.ApplicationInsights;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		ApplicationInsights.attach();
		SpringApplication.run(App.class, args);
	}

	@Bean
	@ConditionalOnProperty(name = "todorepository", havingValue = "mongo", matchIfMissing = false)
	public ToDoRepository prodMongoRepository() {
		return new MongoDBToDoRepository();
	}

	@Bean
	@ConditionalOnProperty(name = "todorepository", havingValue = "inmemory", matchIfMissing = false)
	public ToDoRepository devToDoRepository() {
		return new InMemoryToDoRepository();
	}

}

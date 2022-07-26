package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import com.microsoft.applicationinsights.attach.ApplicationInsights;

@SpringBootApplication
public class App {

	static {
		ApplicationInsights.attach();
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	@ConditionalOnProperty(name = "todorepository", havingValue = "mongo", matchIfMissing = false)
	public TodoListRepository prodMongoRepository() {
		return new MongoDBTodoListRepositoryImpl();
	}

	@Bean
	@ConditionalOnProperty(name = "todorepository", havingValue = "inmemory", matchIfMissing = false)
	public TodoListRepository devToDoRepository() {
		return new InMemoryTodoListRepositoryImpl();
	}

}

package com.example;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.microsoft.applicationinsights.attach.ApplicationInsights;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		ApplicationInsights.attach();
		SpringApplication.run(App.class, args);
	}

	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
			}
		};
	}

	@Value("${spring.data.mongodb.uri:}")
	private String connectionString;

	@Bean
	@ConditionalOnProperty(name = "todorepository", havingValue = "mongo")
	ToDoRepository prodMongoRepository() {
		var connectionStringObj = new ConnectionString(connectionString);
		var mongoClientSettings = MongoClientSettings.builder()
				.applyConnectionString(connectionStringObj)
				.build();

		var mongoClient = MongoClients.create(mongoClientSettings);

		return new CosmosDBToDoRepository(mongoClient, "Todo");
	}

	@Bean
	@ConditionalOnProperty(name = "todorepository", havingValue = "inmemory")
	ToDoRepository getToDoRepository() {
		return new InMemoryToDoRepository();
	}

	class MongoOffsetDateTimeFromStringConverter implements Converter<String, OffsetDateTime> {
		@Override
		public OffsetDateTime convert(String source) {
			return source == null ? null : OffsetDateTime.parse(source);
		}
	}

	class MongoStringFromOffsetDateTimeConverter implements Converter<OffsetDateTime, String> {
		@Override
		public String convert(OffsetDateTime source) {
			return source == null ? null : source.toString();
		}
	}

	@Bean
	MongoCustomConversions offSetConversions() {
		var converterList = new ArrayList<Converter<?, ?>>();
		converterList.add(new MongoOffsetDateTimeFromStringConverter());
		converterList.add(new MongoStringFromOffsetDateTimeConverter());
		return new MongoCustomConversions(converterList);
	}

}

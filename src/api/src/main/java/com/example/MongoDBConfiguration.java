package com.example;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.mongodb.ConnectionString;

@Configuration
@ConditionalOnProperty(name = "todorepository", havingValue = "mongo", matchIfMissing = false)
public class MongoDBConfiguration {

    // Obtains programmatically from Key Vault
    @Value("${azure.keyvault.url:#{null}}")
    private String keyVaultUrl;

    // Hardcoded URL in the properties file
    @Value("${spring.data.mongodb.uri:#{null}}")
    private String mongoUri;

    private String getMongoURL() {
        if (mongoUri != null) {
            System.out.println("Using mongo uri: " + mongoUri);
            return mongoUri;
        }

        // Fallback to programmatically getting value from Key Vault
        var secretClient = new SecretClientBuilder()
                .vaultUrl(keyVaultUrl)
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();

        var mongoUrl = secretClient.getSecret("AZURE-COSMOS-CONNECTION-STRING").getValue();
        return mongoUrl;
    }

    @Bean
    MongoClientSettingsBuilderCustomizer mongoClientSettingsBuilderCustomizer() {
        return builder -> {
            var connectionStringObj = new ConnectionString(MongoDBConfiguration.this.getMongoURL());
            builder.applyConnectionString(connectionStringObj);
        };
    }

    @Bean
    public MongoCustomConversions offSetConversions() {
        var converterList = new ArrayList<Converter<?, ?>>();
        converterList.add(new MongoOffsetDateTimeFromStringConverter());
        converterList.add(new MongoStringFromOffsetDateTimeConverter());
        return new MongoCustomConversions(converterList);
    }

    public static class MongoOffsetDateTimeFromStringConverter implements Converter<String, OffsetDateTime> {
        @Override
        public OffsetDateTime convert(String source) {
            return source == null ? null : OffsetDateTime.parse(source);
        }
    }

    public static class MongoStringFromOffsetDateTimeConverter implements Converter<OffsetDateTime, String> {
        @Override
        public String convert(OffsetDateTime source) {
            return source == null ? null : source.toString();
        }
    }

}

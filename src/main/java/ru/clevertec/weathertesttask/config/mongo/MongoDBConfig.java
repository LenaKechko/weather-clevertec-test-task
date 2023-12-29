package ru.clevertec.weathertesttask.config.mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import static java.util.Arrays.asList;

@Configuration
public class MongoDBConfig {

    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(asList(
                new ZonedDateTimeToDocumentConverter(),
                new DocumentToZonedDateTimeConverter()
        ));
    }
}

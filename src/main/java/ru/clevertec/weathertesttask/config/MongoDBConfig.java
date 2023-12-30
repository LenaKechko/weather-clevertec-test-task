package ru.clevertec.weathertesttask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import ru.clevertec.weathertesttask.util.convert.DocumentToZonedDateTimeConverter;
import ru.clevertec.weathertesttask.util.convert.ZonedDateTimeToDocumentConverter;

import static java.util.Arrays.asList;

/**
 * Класс для настройки работы MongoDB c типом данных ZonedDateTime
 */
@Configuration
public class MongoDBConfig {

    /**
     * Бин для указания классов-конвертирования из ZonedDateTimed в объект для БД и обратно
     *
     * @return объект с настройками конвертирования
     */
    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(asList(
                new ZonedDateTimeToDocumentConverter(),
                new DocumentToZonedDateTimeConverter()
        ));
    }
}

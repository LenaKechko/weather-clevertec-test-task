package ru.clevertec.weathertesttask.config.mongo;


import org.bson.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import static ru.clevertec.weathertesttask.config.mongo.ZonedDateTimeToDocumentConverter.DATE_TIME;
import static ru.clevertec.weathertesttask.config.mongo.ZonedDateTimeToDocumentConverter.ZONE;

/**
 * Класс для настройки конвертирования данных из БД в объект ZonedDateTime
 */
@Component
@ReadingConverter
public class DocumentToZonedDateTimeConverter implements Converter<Document, ZonedDateTime> {

    /**
     * Метод определяющий правила конвертирования
     *
     * @param document the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return объект ZonedDateTime
     */
    @Override
    public ZonedDateTime convert(@Nullable Document document) {
        if (document == null) return null;

        Date dateTime = document.getDate(DATE_TIME);
        String zoneId = document.getString(ZONE);
        ZoneId zone = ZoneId.of(zoneId);

        return ZonedDateTime.ofInstant(dateTime.toInstant(), zone);
    }
}

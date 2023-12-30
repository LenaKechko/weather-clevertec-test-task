package ru.clevertec.weathertesttask.util.convert;


import org.bson.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

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

        Date dateTime = document.getDate(ZonedDateTimeToDocumentConverter.DATE_TIME);
        String zoneId = document.getString(ZonedDateTimeToDocumentConverter.ZONE);
        ZoneId zone = ZoneId.of(zoneId);

        return ZonedDateTime.ofInstant(dateTime.toInstant(), zone);
    }
}

package ru.clevertec.weathertesttask.util.convert;

import org.bson.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Класс для настройки конвертирования данных из ZonedDateTime в объект БД
 */
@Component
@WritingConverter
public class ZonedDateTimeToDocumentConverter implements Converter<ZonedDateTime, Document> {

    static final String DATE_TIME = "dateTime";
    static final String ZONE = "zone";

    /**
     * Метод определяющий правила конвертирования
     *
     * @param zonedDateTime the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return объект формата bson
     */
    @Override
    public Document convert(@Nullable ZonedDateTime zonedDateTime) {
        if (zonedDateTime == null) return null;

        Document document = new Document();
        document.put(DATE_TIME, Date.from(zonedDateTime.toInstant()));
        document.put(ZONE, zonedDateTime.getZone().getId());
        document.put("offset", zonedDateTime.getOffset().toString());
        return document;
    }
}

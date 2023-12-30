package ru.clevertec.weathertesttask.util.convert;

import org.bson.Document;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class DocumentToZonedDateTimeConverterTest {

    private final DocumentToZonedDateTimeConverter documentConverter = new DocumentToZonedDateTimeConverter();

    @Test
    void convertShouldReturnZonedDateTime() {
        // given
        Date date = Date.from(Instant.now());
        ZoneId zone = ZoneId.of(ZoneId.systemDefault().getId());
        ZoneOffset offset = ZoneId.systemDefault().getRules().getStandardOffset(Instant.now());
        Document document = new Document();
        document.put("dateTime", date);
        document.put("zone", zone.toString());
        document.put("offset", offset.toString());

        // when
        ZonedDateTime actual = documentConverter.convert(document);

        // then
        assertThat(Date.from(actual.toInstant()))
                .isEqualTo(date);
        assertThat(actual.getZone())
                .isEqualTo(zone);
        assertThat(actual.getOffset())
                .isEqualTo(offset);
    }

    @Test
    void convertShouldReturnNull() {
        // given

        // when
        ZonedDateTime actual = documentConverter.convert(null);

        // then
        assertNull(actual);
    }
}
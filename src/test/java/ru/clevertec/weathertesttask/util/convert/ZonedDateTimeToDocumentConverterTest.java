package ru.clevertec.weathertesttask.util.convert;

import org.bson.Document;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class ZonedDateTimeToDocumentConverterTest {

    private final ZonedDateTimeToDocumentConverter documentConverter = new ZonedDateTimeToDocumentConverter();

    @Test
    void convertShouldReturnDocument() {
        // given
        ZonedDateTime expectedDate = ZonedDateTime.now();

        // when
        Document actual = documentConverter.convert(expectedDate);
        System.out.println(actual);

        // then
        assertThat(actual.get("dateTime"))
                .isEqualTo(Date.from(expectedDate.toInstant()));
        assertThat(actual.get("zone"))
                .isEqualTo(expectedDate.getZone().toString());
        assertThat(actual.get("offset"))
                .isEqualTo(expectedDate.getOffset().toString());
    }

    @Test
    void convertShouldReturnNull() {
        // given

        // when
        Document actual = documentConverter.convert(null);

        // then
        assertNull(actual);
    }
}
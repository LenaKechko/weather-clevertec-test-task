package ru.clevertec.weathertesttask.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * Модель для временной зоны
 *
 * @param nameTimeZone  буквенное определение локации
 * @param countTimeZone смещение времени по UTC
 */
public record TZInfoModel(@JsonProperty("name") ZoneId nameTimeZone,
                          @JsonProperty("abbr") ZoneOffset countTimeZone) {
}

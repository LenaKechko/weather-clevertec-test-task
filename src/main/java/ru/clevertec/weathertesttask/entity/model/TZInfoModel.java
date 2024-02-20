package ru.clevertec.weathertesttask.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZoneId;

/**
 * Модель для временной зоны
 *
 * @param nameTimeZone  буквенное определение локации
 */
public record TZInfoModel(@JsonProperty("name") ZoneId nameTimeZone) {
}

package ru.clevertec.weathertesttask.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Объект для временной зоны
 *
 * @param tzInfo содержит модель
 */
public record InfoModel(@JsonProperty("tzinfo") TZInfoModel tzInfo) {
}

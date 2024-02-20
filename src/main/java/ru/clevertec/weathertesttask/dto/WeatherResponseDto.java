package ru.clevertec.weathertesttask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.FieldNameConstants;
import ru.clevertec.weathertesttask.entity.model.WeatherModel;

import java.time.ZonedDateTime;

/**
 * Объект, в который преобразуются данные от внешнего api
 *
 * @param date  время запроса погоды
 * @param model модель текущей погоды
 */
@FieldNameConstants
public record WeatherResponseDto(
        @JsonProperty("date") ZonedDateTime date,
        @JsonProperty("now") WeatherModel model) {
}

package ru.clevertec.weathertesttask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.clevertec.weathertesttask.entity.model.WeatherModel;

import java.time.ZonedDateTime;

/**
 * Объект, в который преобразуются данные от внешнего api
 *
 * @param date  вемя запроса погоды
 * @param model модель текущей погоды
 */
public record WeatherResponseDto(@JsonProperty("date") ZonedDateTime date,
                                 @JsonProperty("now") WeatherModel model) {
}

package ru.clevertec.weathertesttask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.clevertec.weathertesttask.entity.model.WeatherModel;

import java.time.ZonedDateTime;

public record WeatherResponseDto(@JsonProperty("date") ZonedDateTime date,
                                     @JsonProperty("now") WeatherModel model) {
}

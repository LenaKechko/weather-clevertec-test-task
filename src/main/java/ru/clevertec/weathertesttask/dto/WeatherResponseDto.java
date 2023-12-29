package ru.clevertec.weathertesttask.dto;

import lombok.Builder;
import ru.clevertec.weathertesttask.entity.model.WeatherModel;

import java.time.ZonedDateTime;

@Builder
public record WeatherResponseDto(ZonedDateTime date,
                                 WeatherModel model) {
}

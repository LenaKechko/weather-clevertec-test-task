package ru.clevertec.weathertesttask.dto;

import lombok.Builder;
import ru.clevertec.weathertesttask.entity.model.WeatherModel;

import java.time.LocalDateTime;

@Builder
public record WeatherResponseDto(LocalDateTime date,
                                 String nameCity,
                                 WeatherModel model) {
}

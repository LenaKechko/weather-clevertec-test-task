package ru.clevertec.weathertesttask.dto;

import ru.clevertec.weathertesttask.entity.model.DayWeatherModel;

import java.time.LocalDate;
import java.util.Map;

public record ForecastWeatherResponseDto(LocalDate date,
                                         Map<String, DayWeatherModel> forecastOfDay) {
}


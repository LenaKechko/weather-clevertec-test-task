package ru.clevertec.weathertesttask.dto;

import ru.clevertec.weathertesttask.entity.model.DayWeatherModel;

import java.time.LocalDate;
import java.util.Map;

/**
 * Объект погоды на несколько дней, в который преобразуются данные от внешнего api
 *
 * @param date          дата
 * @param forecastOfDay погода в разные периоды времени дня
 */
public record ForecastWeatherResponseDto(LocalDate date,
                                         Map<String, DayWeatherModel> forecastOfDay) {
}


package ru.clevertec.weathertesttask.sevice;

import ru.clevertec.weathertesttask.dto.ForecastWeatherResponseDto;
import ru.clevertec.weathertesttask.model.WeatherRequest;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;

import java.util.List;

/**
 * Интерфейс для сервиса
 */

public interface WeatherService {

    WeatherResponseDto getWeather(WeatherRequest request);

    List<WeatherResponseDto> getWeathers(WeatherRequest request);

    List<ForecastWeatherResponseDto> getForecastWeather(WeatherRequest request);
}

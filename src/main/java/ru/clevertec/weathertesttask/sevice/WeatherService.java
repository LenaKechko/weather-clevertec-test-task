package ru.clevertec.weathertesttask.sevice;

import ru.clevertec.weathertesttask.model.WeatherRequest;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;

public interface WeatherService {

    WeatherResponseDto getWeather(WeatherRequest request);
}

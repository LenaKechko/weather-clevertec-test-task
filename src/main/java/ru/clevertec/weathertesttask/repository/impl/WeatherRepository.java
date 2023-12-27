package ru.clevertec.weathertesttask.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.weathertesttask.model.IYandexResponse;
import ru.clevertec.weathertesttask.entity.YandexResponse;
import ru.clevertec.weathertesttask.repository.IWeatherRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WeatherRepository implements IWeatherRepository {

    private final IYandexResponse response;

    public Optional<YandexResponse> getWeather(Double longitude, Double latitude, Integer limit) {
        System.out.println("I am here!!");
        System.out.println(response.getWeather(longitude, latitude, limit));
//        weatherRepository.insert(weatherApiResponse);
        return Optional.ofNullable(response.getWeather(longitude, latitude, limit));
    }
}

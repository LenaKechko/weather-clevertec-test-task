package ru.clevertec.weathertesttask.repository;

import ru.clevertec.weathertesttask.entity.YandexResponse;

import java.util.Optional;

/**
 * Интерфейс для работы слоя репозиторий
 */
public interface IWeatherRepository {

    Optional<YandexResponse> getWeather(Double longitude, Double latitude, Integer limit);
}

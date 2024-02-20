package ru.clevertec.weathertesttask.repository.impl;

import feign.FeignException;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.weathertesttask.entity.IYandexResponse;
import ru.clevertec.weathertesttask.entity.YandexResponse;
import ru.clevertec.weathertesttask.repository.IWeatherRepository;

import java.util.Optional;

/**
 * Класс для работы со слоем репозитория
 * Выполняет запросы к внешнему Api
 * Аннотация @Component указывает на то, что класс является бином
 * Аннотация @RequiredArgsConstructor заменяет написание конструктора со всеми полями класса
 */
@Component
@RequiredArgsConstructor
public class WeatherRepository implements IWeatherRepository {

    /**
     * Поле для работы с ответом внешнего Api
     */
    private final IYandexResponse response;

    /**
     * Метод для получения данных от внешнего api Яндекс.Погода
     *
     * @param longitude координата долготы
     * @param latitude  координата широты
     * @param limit     количество дней, если необходимо
     * @return Optional<YandexResponse> для дальнейшей обработки ошибочных данных
     */
    @Timed("gettingWeather")
    public Optional<YandexResponse> getWeather(Double longitude, Double latitude, Integer limit) {
        try{
            return Optional.ofNullable(response.getWeather(longitude, latitude, limit));
        } catch (FeignException.FeignClientException e) {
            return Optional.empty();
        }
    }
}

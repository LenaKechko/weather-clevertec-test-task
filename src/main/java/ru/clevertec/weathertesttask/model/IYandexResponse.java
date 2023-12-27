package ru.clevertec.weathertesttask.model;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.clevertec.weathertesttask.config.FeignConfig;
import ru.clevertec.weathertesttask.entity.YandexResponse;

@FeignClient(name = "weather", configuration = FeignConfig.class, url = "https://api.weather.yandex.ru/v2/forecast")
public interface IYandexResponse {

    @GetMapping
    YandexResponse getWeather(
            @RequestParam(name = "lon") double longitude,
            @RequestParam(name = "lat") double latitude,
            @RequestParam(name = "limit") int limit);
}

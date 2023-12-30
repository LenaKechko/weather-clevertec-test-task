package ru.clevertec.weathertesttask.entity;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.clevertec.weathertesttask.config.FeignConfig;

/**
 * Интерфейс для подключения и отравки запроса внешнесу сервису
 * В аннотации @FeignClient выполняются настройки подключения
 */
@FeignClient(name = "weather", configuration = FeignConfig.class)
public interface IYandexResponse {

    /**
     * Rest-запрос к внешнему сервису
     * @param longitude координата долготы
     * @param latitude  координата широты
     * @param limit     количество дней
     * @return объект типа YandexResponse
     */
    @GetMapping
    YandexResponse getWeather(
            @RequestParam(name = "lon") double longitude,
            @RequestParam(name = "lat") double latitude,
            @RequestParam(name = "limit") int limit);
}

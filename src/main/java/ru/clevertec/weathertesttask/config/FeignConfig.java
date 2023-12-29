package ru.clevertec.weathertesttask.config;

import feign.Logger;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Класс для настройки Feign Cients для подклчения к внешнему api
 */
@Configuration
@RequiredArgsConstructor
public class FeignConfig {

    /**
     * Поле для работы с настройками Яндекс.Погоды
     */
    private final YandexConfig yandexConfig;

    /**
     * Бин настройки подключения к внешнему api
     *
     * @return
     */
    @Bean
    public RequestInterceptor interceptor() {
        return requestTemplate -> {
            requestTemplate.header("X-Yandex-API-Key", yandexConfig.getKey());
            requestTemplate.header("Content-Type", "application/json");
        };
    }

    /**
     * Настройка логирования
     *
     * @return уровень логирования
     */
    @Bean
    Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }
}

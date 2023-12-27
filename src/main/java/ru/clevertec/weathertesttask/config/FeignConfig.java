package ru.clevertec.weathertesttask.config;

import feign.Logger;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FeignConfig {

    private final YandexConfig yandexConfig;

    @Bean
    public RequestInterceptor interceptor() {
        return requestTemplate -> {
            requestTemplate.header("X-Yandex-API-Key", yandexConfig.getKey());
            requestTemplate.header("Content-Type", "application/json");
        };
    }

    @Bean
    Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }
}

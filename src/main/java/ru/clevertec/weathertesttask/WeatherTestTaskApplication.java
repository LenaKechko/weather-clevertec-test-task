package ru.clevertec.weathertesttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ru.clevertec.weathertesttask.config.YandexConfig;

/**
 * Класс для запуска приложения
 * Аннотация @EnableFeignClients служит для подключения и конфигурации пакета FeignClients,
 * который работает свнешним API
 * Аннотаия @EnableConfigurationProperties подключает файл конфигурации с Яндекс.Погода
 */

@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties(YandexConfig.class)
public class WeatherTestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherTestTaskApplication.class, args);
    }

}

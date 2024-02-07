package ru.clevertec.weathertesttask.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Класс для настройки подключения к внешнему api Яндекс.Погода
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "yandex")
public class YandexConfig {

    /**
     * Поле для ключа для подключения
     */
    String key;
}

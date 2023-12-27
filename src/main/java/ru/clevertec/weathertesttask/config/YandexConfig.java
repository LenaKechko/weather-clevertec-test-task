package ru.clevertec.weathertesttask.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "yandex")
public class YandexConfig {
    String key;
}

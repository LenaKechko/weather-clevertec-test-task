package ru.clevertec.weathertesttask.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.clevertec.weathertesttask.entity.model.WeatherModel;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "weather")
public record WeatherResponse(@Id UUID id, LocalDateTime date,
                              String nameCity,
                              WeatherModel model) {
    WeatherResponse(LocalDateTime date,
                    String nameCity,
                    WeatherModel model) {
        this(UUID.randomUUID(), date, nameCity, model);
    }
}


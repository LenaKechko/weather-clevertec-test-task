package ru.clevertec.weathertesttask.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.clevertec.weathertesttask.entity.model.WeatherModel;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "weather")
public class WeatherResponse {

    @Id
    private UUID id;
    private LocalDateTime date;
    private String nameCity;
    private WeatherModel model;
//    WeatherResponse(LocalDateTime date,
//                    String nameCity,
//                    WeatherModel model) {
//        this(UUID.randomUUID(), date, nameCity, model);
//    }
}


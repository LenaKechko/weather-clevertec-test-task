package ru.clevertec.weathertesttask.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.web.JsonPath;

import java.time.LocalDateTime;
import java.util.List;

//@JsonProperty("geo_object[locality[name]]") nameLocation,


public record YandexResponse(@JsonPath("$.geo_object.locality.name") String nameLocation,
                             @JsonProperty("now_dt") LocalDateTime date,
                             @JsonProperty("fact") FactModel model,
                             @JsonProperty("$.fact.temp") String[] modelTemp) {

    public record LocationModel(@JsonProperty("$..locality.name") String nameCity) {
    }

    public record FactModel(
            @JsonProperty("temp") Double temperature,
            @JsonProperty("feels_like") Double feelsTemperature,
            @JsonProperty("condition") String condition,
            @JsonProperty("wind_speed") Double windSpeed,
            @JsonProperty("wind_gust") Double windGust,
            @JsonProperty("wind_dir") String windDir,
            @JsonProperty("pressure_mm") Integer pressureInMm) {
    }
}


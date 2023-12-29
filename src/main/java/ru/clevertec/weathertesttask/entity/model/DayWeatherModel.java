package ru.clevertec.weathertesttask.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DayWeatherModel(
        @JsonProperty("temp_min") Double minTemperature,
        @JsonProperty("temp_avg") Double avgTemperature,
        @JsonProperty("temp_max") Double maxTemperature,
        @JsonProperty("feels_like") Double feelsTemperature,
        @JsonProperty("wind_speed") Double windSpeed,
        @JsonProperty("wind_gust") Double windGust,
        @JsonProperty("wind_dir") String windDir,
        @JsonProperty("pressure_mm") Integer pressureInMm,
        @JsonProperty("condition") String condition) {

}

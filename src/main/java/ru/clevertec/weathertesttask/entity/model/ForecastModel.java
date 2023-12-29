package ru.clevertec.weathertesttask.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record ForecastModel(@JsonProperty LocalDate date,
                            @JsonProperty("parts") PartOfDay partOfDayList) {

    public record PartOfDay(@JsonProperty DayWeatherModel day,
                            @JsonProperty DayWeatherModel morning,
                            @JsonProperty DayWeatherModel night,
                            @JsonProperty DayWeatherModel evening) {
    }
}



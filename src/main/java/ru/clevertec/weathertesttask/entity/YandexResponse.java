package ru.clevertec.weathertesttask.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;


public record YandexResponse(@JsonProperty("now_dt") ZonedDateTime date,
                             @JsonProperty("fact") FactModel model,
                             @JsonProperty("info") InfoModel info,
                             @JsonProperty("forecasts") List<ForecastModel> forecast) {

    public record FactModel(
            @JsonProperty("temp") Double temperature,
            @JsonProperty("feels_like") Double feelsTemperature,
            @JsonProperty("condition") String condition,
            @JsonProperty("wind_speed") Double windSpeed,
            @JsonProperty("wind_gust") Double windGust,
            @JsonProperty("wind_dir") String windDir,
            @JsonProperty("pressure_mm") Integer pressureInMm) {
    }

    public record InfoModel(@JsonProperty("tzinfo") TZInfoModel tzInfo) {
    }

    public record TZInfoModel(@JsonProperty("name") ZoneId nameTimeZone,
                              @JsonProperty("abbr") ZoneOffset countTimeZone) {
    }

    public record ForecastModel(@JsonProperty LocalDate date,
                                @JsonProperty("parts") PartOfDay partOfDayList) {
    }

    public record PartOfDay(@JsonProperty DayWeatherModel day,
                            @JsonProperty DayWeatherModel morning,
                            @JsonProperty DayWeatherModel night,
                            @JsonProperty DayWeatherModel evening) {
    }

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
}


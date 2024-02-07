package ru.clevertec.weathertesttask.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.experimental.FieldNameConstants;

/**
 * Модель прогноза погоды на текущий день
 *
 * @param temperature      температура на данный момент
 * @param feelsTemperature ощущаемая температура
 * @param condition        погодные условия
 * @param windSpeed        скорость ветра
 * @param windGust         скорость поряов ветра
 * @param windDir          напавление ветра
 * @param pressureInMm     давление в (мм.рт.ст.)
 */
@Builder
@FieldNameConstants
public record WeatherModel(
        @JsonProperty("temp") Double temperature,
        @JsonProperty("feels_like") Double feelsTemperature,
        @JsonProperty("condition") String condition,
        @JsonProperty("wind_speed") Double windSpeed,
        @JsonProperty("wind_gust") Double windGust,
        @JsonProperty("wind_dir") String windDir,
        @JsonProperty("pressure_mm") Integer pressureInMm) {
}

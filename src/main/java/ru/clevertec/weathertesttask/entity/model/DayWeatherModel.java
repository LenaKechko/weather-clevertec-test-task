package ru.clevertec.weathertesttask.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.FieldNameConstants;

/**
 * Модель для погоды в разные временные промежутки дня
 *
 * @param minTemperature   минимальная температура
 * @param avgTemperature   средняя температура
 * @param maxTemperature   максимальная температура
 * @param feelsTemperature ощущаемая температура
 * @param windSpeed        скорость верта
 * @param windGust         скорость порывов ветра
 * @param windDir          направление ветра
 * @param pressureInMm     давление (мм.рт.ст.)
 * @param condition        погодные условия
 */
@FieldNameConstants
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

package ru.clevertec.weathertesttask.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

/**
 * Модель прогноза погоды на несколько дней для парсинга json
 *
 * @param date          дата прогноза
 * @param partOfDayList модель для прогноза в разные время ня
 */

public record ForecastModel(@JsonProperty LocalDate date,
                            @JsonProperty("parts") PartOfDay partOfDayList) {

    /**
     * Модель для погоды в разное время дня
     *
     * @param day     объект погоды днем
     * @param morning объект погоды утром
     * @param night   объект погоды ночью
     * @param evening объект погоды вечером
     */
    public record PartOfDay(@JsonProperty DayWeatherModel day,
                            @JsonProperty DayWeatherModel morning,
                            @JsonProperty DayWeatherModel night,
                            @JsonProperty DayWeatherModel evening) {
    }
}



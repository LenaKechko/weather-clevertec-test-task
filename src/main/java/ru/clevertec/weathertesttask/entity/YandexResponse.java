package ru.clevertec.weathertesttask.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.clevertec.weathertesttask.entity.model.ForecastModel;
import ru.clevertec.weathertesttask.entity.model.WeatherModel;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Класс для обработки приходящего json от внешнего API. Для модели выбираются необходимые узлы
 *
 * @param date     текущая дата
 * @param model    модель для прогноза погоды на текущий день
 * @param info     модель с информацией о часовом поясе
 * @param forecast модель с прогнозом на один или несколько дней
 */
public record YandexResponse(@JsonProperty("now_dt") ZonedDateTime date,
                             @JsonProperty("fact") WeatherModel model,
                             @JsonProperty("info") InfoModel info,
                             @JsonProperty("forecasts") List<ForecastModel> forecast) {

    /**
     * Объект для временной зоны
     *
     * @param tzInfo содержит модель
     */
    public record InfoModel(@JsonProperty("tzinfo") TZInfoModel tzInfo) {
    }

    /**
     * Модель для временной зоны
     *
     * @param nameTimeZone  буквенное определение локации
     * @param countTimeZone смещение времени по UTC
     */
    public record TZInfoModel(@JsonProperty("name") ZoneId nameTimeZone,
                              @JsonProperty("abbr") ZoneOffset countTimeZone) {
    }

}


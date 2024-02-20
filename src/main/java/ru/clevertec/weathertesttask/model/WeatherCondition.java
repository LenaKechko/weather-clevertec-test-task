package ru.clevertec.weathertesttask.model;

import lombok.Getter;

/**
 * Перечисление для погодных условий, полученных от внешнего api
 */
@Getter
public enum WeatherCondition {

    /**
     * Перечислены допустимые значения
     */
    CLEAR("ясно"),
    PARTLY_CLOUDY("малооблачно"),
    CLOUDY("облачно с прояснениями"),
    OVERCAST("пасмурно"),
    LIGHT_RAIN("небольшой дождь"),
    RAIN("дождь"),
    HEAVY_RAIN("сильный дождь"),
    SHOWERS("ливень"),
    WET_SNOW("дождь со снегом"),
    LIGHT_SNOW("небольшой снег"),
    SNOW("снег"),
    SNOW_SHOWERS("снегопад"),
    HAIL("град"),
    THUNDERSTORM("гроза"),
    THUNDERSTORM_WITH_RAIN("дождь с грозой"),
    THUNDERSTORM_WITH_HAIL("гроза с градом");

    /**
     * Поле определяющее имя константы
     */
    private final String nameCondition;

    /**
     * Конструктор
     *
     * @param nameCondition имя
     */
    WeatherCondition(String nameCondition) {
        this.nameCondition = nameCondition;
    }

    /**
     * Метод для возвращения значения константы по ее имени
     *
     * @param condition константа
     * @return значение константы
     */
    public static String getCondition(String condition) {
        return WeatherCondition.valueOf(condition.replaceAll("-", "_").toUpperCase()).getNameCondition();
    }

}

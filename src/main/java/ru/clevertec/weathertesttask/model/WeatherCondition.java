package ru.clevertec.weathertesttask.model;

import lombok.Getter;

@Getter
public enum WeatherCondition {

    CLEAR("ясно"),
    PARTLY_CLOUDY("малооблачно"),
    CLOUDY("облачно с прояснениями"),
    OVERCAST("пасмурно"),
    LIGHT_RAIN("небольшой дождь"),
    RAIN("дождь"),
    HEAVY_RAIN("сильный дождь"),
    SHOWERS("ливень"),
    WET_SNOW("дождь со снегом"),
    LIGHT_SHOW("небольшой снег"),
    SNOW("снег"),
    SNOW_SHOWERS("снегопад"),
    HAIL("град"),
    THUNDERSTORM("гроза"),
    THUNDERSTORM_WITH_RAIN("дождь с грозой"),
    THUNDERSTORM_WITH_HAIL("гроза с градом");

    private final String nameCondition;

    WeatherCondition(String nameCondition) {
        this.nameCondition = nameCondition;
    }

}

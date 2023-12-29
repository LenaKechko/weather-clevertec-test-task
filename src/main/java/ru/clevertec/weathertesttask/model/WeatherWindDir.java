package ru.clevertec.weathertesttask.model;

import lombok.Getter;

/**
 * Перечисление для направления ветра, полученного от внешнего api
 */
@Getter
public enum WeatherWindDir {

    /**
     * Перечислены допустимые значения
     */
    NW("северо-западное"),
    N("северное"),
    NE("северо-восточное"),
    E("восточное"),
    SE("юго-восточное"),
    S("южное"),
    SW("юго-западное"),
    W("западное"),
    C("штиль");

    /**
     * Поле определяющее имя константы
     */
    private final String nameWindDir;

    /**
     * Конструктор
     *
     * @param nameWindDir имя
     */
    WeatherWindDir(String nameWindDir) {
        this.nameWindDir = nameWindDir;
    }

    /**
     * Метод для возвращения значения константы по ее имени
     *
     * @param windDir константа
     * @return значение константы
     */
    public static String getWindDir(String windDir) {
        return WeatherWindDir.valueOf(windDir.replaceAll("-", "_").toUpperCase()).getNameWindDir();
    }
}

package ru.clevertec.weathertesttask.model;

import lombok.Getter;

@Getter
public enum WeatherWindDir {
    NW("северо-западное"),
    N("северное"),
    NE("северо-восточное"),
    E("восточное"),
    SE("юго-восточное"),
    S("южное"),
    SW("юго-западное"),
    W("западное"),
    C("штиль");

    private final String nameWindDir;

    WeatherWindDir(String nameWindDir) {
        this.nameWindDir = nameWindDir;
    }

    public static String getWindDir(String windDir) {
        return WeatherWindDir.valueOf(windDir.toUpperCase()).getNameWindDir();
    }
}

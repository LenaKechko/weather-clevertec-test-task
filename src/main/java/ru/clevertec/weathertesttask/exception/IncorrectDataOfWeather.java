package ru.clevertec.weathertesttask.exception;

public class IncorrectDataOfWeather extends RuntimeException {

    public IncorrectDataOfWeather(Double longitude, Double latitude) {
        super("Incorrect longitude = " + longitude + "or latitude = " + latitude);
    }
}

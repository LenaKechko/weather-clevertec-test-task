package ru.clevertec.weathertesttask.model;

public record WeatherRequest(Double longitude, Double latitude, Integer limit) {

    public WeatherRequest {
        if (longitude == null) {
            longitude = 52.4345;
        }
        if (latitude == null) {
            latitude = 30.9754;
        }
        if (limit == null) {
            limit = 1;
        }
    }
}

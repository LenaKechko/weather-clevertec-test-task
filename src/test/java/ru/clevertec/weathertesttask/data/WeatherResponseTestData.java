package ru.clevertec.weathertesttask.data;

import lombok.Builder;
import lombok.Getter;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
import ru.clevertec.weathertesttask.entity.WeatherResponse;
import ru.clevertec.weathertesttask.entity.model.WeatherModel;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Builder(setterPrefix = "with", toBuilder = true)
public class WeatherResponseTestData {

    @Builder.Default
    private UUID id = UUID.randomUUID();

    @Getter
    @Builder.Default
    private ZonedDateTime date = ZonedDateTime.of(LocalDateTime.MAX, ZoneId.systemDefault());

    @Builder.Default
    private WeatherModel model = WeatherModelTestData.builder().build().buildWeatherModel();


    public WeatherResponse buildWeather() {
        return new WeatherResponse(id, date, model);
    }

    public WeatherResponseDto buildWeatherDto() {
        return new WeatherResponseDto(date, model);
    }
}

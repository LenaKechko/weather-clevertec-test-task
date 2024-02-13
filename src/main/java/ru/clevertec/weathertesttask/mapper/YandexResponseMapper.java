package ru.clevertec.weathertesttask.mapper;

import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
import ru.clevertec.weathertesttask.entity.YandexResponse;
import ru.clevertec.weathertesttask.entity.model.WeatherModel;
import ru.clevertec.weathertesttask.model.WeatherCondition;
import ru.clevertec.weathertesttask.model.WeatherWindDir;

public interface YandexResponseMapper {

    static WeatherResponseDto toWeatherResponseDto(YandexResponse response) {
        return new WeatherResponseDto(
                response.date().withZoneSameInstant(response.info().tzInfo().nameTimeZone()),
                new WeatherModel(response.model().temperature(),
                        response.model().feelsTemperature(),
                        WeatherCondition.getCondition(response.model().condition()),
                        response.model().windSpeed(),
                        response.model().windGust(),
                        WeatherWindDir.getWindDir(response.model().windDir()),
                        response.model().pressureInMm()
                )
        );
    }
}

package ru.clevertec.weathertesttask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
import ru.clevertec.weathertesttask.entity.WeatherResponse;

@Mapper(componentModel = "spring")
public interface WeatherResponseMapper {

    @Mapping(target = "id", expression ="java(java.util.UUID.randomUUID())")
    WeatherResponse toWeatherResponse(WeatherResponseDto responseDto);

    WeatherResponseDto fromWeatherResponse(WeatherResponse response);
}

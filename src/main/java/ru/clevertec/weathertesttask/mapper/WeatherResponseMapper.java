package ru.clevertec.weathertesttask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
import ru.clevertec.weathertesttask.entity.WeatherResponse;

/**
 * Интерфейс для мапинга объектов WeatherResponseDto и WeatherResponse
 * Аннотация @Mapper отвечает за создание реализации интерфейса
 */
@Mapper(componentModel = "spring")
public interface WeatherResponseMapper {

    /**
     * Маппинг из WeatherResponseDto в WeatherResponse
     * В объекте WeatherResponse автоматически создается рандомный id
     *
     * @param responseDto объект DTO
     * @return объект для записи в БД
     */
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    WeatherResponse toWeatherResponse(WeatherResponseDto responseDto);

    /**
     * Маппинг из WeatherResponse в WeatherResponseDto
     *
     * @param response объект entity
     * @return объект без id
     */
    WeatherResponseDto fromWeatherResponse(WeatherResponse response);
}

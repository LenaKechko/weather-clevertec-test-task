package ru.clevertec.weathertesttask.mapper;

import ru.clevertec.weathertesttask.dto.ForecastWeatherResponseDto;
import ru.clevertec.weathertesttask.entity.model.ForecastModel;

import java.util.Map;

/**
 * Интерфейс для маппинга объекта проноза на несколько дней
 */
public interface ForecastModelMapper {

    /**
     * Статический метод. Маппит из объекта пришедшего от внешнего api ForecastModel
     * в ForecastWeatherResponseDto
     *
     * @param model объект пришедший от внешнего api
     * @return объект в формате ForecastWeatherResponseDto
     */
    static ForecastWeatherResponseDto toForecastWeatherResponseDto(ForecastModel model) {
        return new ForecastWeatherResponseDto(model.date(),
                Map.of("morning", model.partOfDayList().morning(),
                        "day", model.partOfDayList().day(),
                        "evening", model.partOfDayList().evening(),
                        "night", model.partOfDayList().night()));
    }
}

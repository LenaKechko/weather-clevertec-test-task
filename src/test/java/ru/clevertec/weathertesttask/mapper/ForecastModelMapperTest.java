package ru.clevertec.weathertesttask.mapper;

import org.junit.jupiter.api.Test;
import ru.clevertec.weathertesttask.data.ForecastModelTestData;
import ru.clevertec.weathertesttask.dto.ForecastWeatherResponseDto;
import ru.clevertec.weathertesttask.entity.model.DayWeatherModel;
import ru.clevertec.weathertesttask.entity.model.ForecastModel;

import static org.assertj.core.api.Assertions.assertThat;

class ForecastModelMapperTest {

    @Test
    void toForecastWeatherResponseDto() {
        // given
        ForecastModel model = ForecastModelTestData.builder()
                .build()
                .buildForecastModel();
        ForecastWeatherResponseDto expected = ForecastModelTestData.builder()
                .build().buildForecastWeatherResponseDto();

        // when
        ForecastWeatherResponseDto actual = ForecastModelMapper.toForecastWeatherResponseDto(model);

        // then
        assertThat(actual)
                .hasFieldOrPropertyWithValue(ForecastWeatherResponseDto.Fields.date, expected.date());
        actual.forecastOfDay().keySet()
                .forEach(index ->
                        assertThat(actual.forecastOfDay().get(index))
                                .hasFieldOrPropertyWithValue(DayWeatherModel.Fields.minTemperature, expected.forecastOfDay().get(index).minTemperature())
                                .hasFieldOrPropertyWithValue(DayWeatherModel.Fields.avgTemperature, expected.forecastOfDay().get(index).avgTemperature())
                                .hasFieldOrPropertyWithValue(DayWeatherModel.Fields.maxTemperature, expected.forecastOfDay().get(index).maxTemperature())
                                .hasFieldOrPropertyWithValue(DayWeatherModel.Fields.feelsTemperature, expected.forecastOfDay().get(index).feelsTemperature())
                                .hasFieldOrPropertyWithValue(DayWeatherModel.Fields.windSpeed, expected.forecastOfDay().get(index).windSpeed())
                                .hasFieldOrPropertyWithValue(DayWeatherModel.Fields.windDir, expected.forecastOfDay().get(index).windDir())
                                .hasFieldOrPropertyWithValue(DayWeatherModel.Fields.windGust, expected.forecastOfDay().get(index).windGust())
                                .hasFieldOrPropertyWithValue(DayWeatherModel.Fields.pressureInMm, expected.forecastOfDay().get(index).pressureInMm())
                                .hasFieldOrPropertyWithValue(DayWeatherModel.Fields.condition, expected.forecastOfDay().get(index).condition()));
    }
}
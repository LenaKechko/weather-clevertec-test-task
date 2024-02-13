package ru.clevertec.weathertesttask.mapper;

import org.junit.jupiter.api.Test;
import ru.clevertec.weathertesttask.data.WeatherResponseTestData;
import ru.clevertec.weathertesttask.data.YandexResponseTestData;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
import ru.clevertec.weathertesttask.entity.YandexResponse;
import ru.clevertec.weathertesttask.entity.model.WeatherModel;

import static org.assertj.core.api.Assertions.assertThat;

class YandexResponseMapperTest {

    @Test
    void weatherResponseDto() {
        // given
        YandexResponse yandexResponse = YandexResponseTestData.builder().build().buildYandexResponse();
        WeatherResponseDto expected = WeatherResponseTestData.builder()
                .build()
                .buildWeatherDto();

        // when
        WeatherResponseDto actual = YandexResponseMapper.toWeatherResponseDto(yandexResponse);

        // then
        assertThat(actual)
                .hasFieldOrPropertyWithValue(WeatherResponseDto.Fields.date, expected.date());

        assertThat(actual.model())
                .hasFieldOrPropertyWithValue(WeatherModel.Fields.temperature, expected.model().temperature())
                .hasFieldOrPropertyWithValue(WeatherModel.Fields.feelsTemperature, expected.model().feelsTemperature())
                .hasFieldOrPropertyWithValue(WeatherModel.Fields.condition, expected.model().condition())
                .hasFieldOrPropertyWithValue(WeatherModel.Fields.windSpeed, expected.model().windSpeed())
                .hasFieldOrPropertyWithValue(WeatherModel.Fields.windDir, expected.model().windDir())
                .hasFieldOrPropertyWithValue(WeatherModel.Fields.windGust, expected.model().windGust())
                .hasFieldOrPropertyWithValue(WeatherModel.Fields.pressureInMm, expected.model().pressureInMm());

    }
}
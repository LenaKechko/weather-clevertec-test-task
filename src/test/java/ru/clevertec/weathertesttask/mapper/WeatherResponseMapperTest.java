package ru.clevertec.weathertesttask.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.weathertesttask.data.WeatherResponseTestData;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
import ru.clevertec.weathertesttask.entity.WeatherResponse;
import ru.clevertec.weathertesttask.entity.model.WeatherModel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WeatherResponseMapperTest {

    private WeatherResponseMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new WeatherResponseMapperImpl();
    }

    @Test
    void toWeatherResponse() {
        // given
        WeatherResponseDto responseDto = WeatherResponseTestData.builder()
                .build()
                .buildWeatherDto();
        WeatherResponse expected = WeatherResponseTestData.builder()
                .withId(null)
                .build()
                .buildWeather();

        // when
        WeatherResponse actual = mapper.toWeatherResponse(responseDto);

        // then
        assertThat(actual)
                .hasFieldOrPropertyWithValue(WeatherResponse.Fields.date, expected.getDate());

        assertThat(actual.getModel())
                .hasFieldOrPropertyWithValue(WeatherModel.Fields.temperature, expected.getModel().temperature())
                .hasFieldOrPropertyWithValue(WeatherModel.Fields.feelsTemperature, expected.getModel().feelsTemperature())
                .hasFieldOrPropertyWithValue(WeatherModel.Fields.condition, expected.getModel().condition())
                .hasFieldOrPropertyWithValue(WeatherModel.Fields.windSpeed, expected.getModel().windSpeed())
                .hasFieldOrPropertyWithValue(WeatherModel.Fields.windDir, expected.getModel().windDir())
                .hasFieldOrPropertyWithValue(WeatherModel.Fields.windGust, expected.getModel().windGust())
                .hasFieldOrPropertyWithValue(WeatherModel.Fields.pressureInMm, expected.getModel().pressureInMm());

        assertNotNull(actual.getId());
    }

    @Test
    void fromWeatherResponse() {
        // given
        WeatherResponse response = WeatherResponseTestData.builder()
                .withId(null)
                .build()
                .buildWeather();
        WeatherResponseDto expected = WeatherResponseTestData.builder()
                .build()
                .buildWeatherDto();

        // when
        WeatherResponseDto actual = mapper.fromWeatherResponse(response);

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
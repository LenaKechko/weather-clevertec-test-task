package ru.clevertec.weathertesttask.sevice.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.weathertesttask.constant.Location;
import ru.clevertec.weathertesttask.data.ForecastModelTestData;
import ru.clevertec.weathertesttask.data.WeatherRequestTestData;
import ru.clevertec.weathertesttask.data.WeatherResponseTestData;
import ru.clevertec.weathertesttask.data.YandexResponseTestData;
import ru.clevertec.weathertesttask.dto.ForecastWeatherResponseDto;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
import ru.clevertec.weathertesttask.entity.YandexResponse;
import ru.clevertec.weathertesttask.entity.model.WeatherModel;
import ru.clevertec.weathertesttask.exception.IncorrectDataOfWeather;
import ru.clevertec.weathertesttask.mapper.YandexResponseMapper;
import ru.clevertec.weathertesttask.model.WeatherRequest;
import ru.clevertec.weathertesttask.repository.impl.WeatherRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WeatherServiceImplTest {

    @Mock
    private WeatherRepository weatherRepository;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    @Test
    void getWeatherShouldReturnWeatherResponseDtoWhenCorrectRequest() {
        // given
        WeatherRequest weatherRequest = WeatherRequestTestData.builder().build().buildWeatherRequest();
        YandexResponse yandexResponse = YandexResponseTestData.builder().build().buildYandexResponse();
        WeatherResponseDto exceptedWeatherResponse = WeatherResponseTestData.builder().build().buildWeatherDto();

        doReturn(Optional.of(yandexResponse))
                .when(weatherRepository).getWeather(
                        weatherRequest.longitude(),
                        weatherRequest.latitude(),
                        weatherRequest.limit()
                );

        // when
        WeatherResponseDto actualWeatherResponse = weatherService.getWeather(weatherRequest);

        // then
        assertEquals(exceptedWeatherResponse, actualWeatherResponse);

        assertThat(actualWeatherResponse)
                .hasFieldOrPropertyWithValue(WeatherResponseDto.Fields.date, exceptedWeatherResponse.date());

        assertThat(actualWeatherResponse.model())
                .hasFieldOrPropertyWithValue(WeatherModel.Fields.temperature, exceptedWeatherResponse.model().temperature())
                .hasFieldOrPropertyWithValue(WeatherModel.Fields.feelsTemperature, exceptedWeatherResponse.model().feelsTemperature())
                .hasFieldOrPropertyWithValue(WeatherModel.Fields.condition, exceptedWeatherResponse.model().condition())
                .hasFieldOrPropertyWithValue(WeatherModel.Fields.windSpeed, exceptedWeatherResponse.model().windSpeed())
                .hasFieldOrPropertyWithValue(WeatherModel.Fields.windGust, exceptedWeatherResponse.model().windGust())
                .hasFieldOrPropertyWithValue(WeatherModel.Fields.windDir, exceptedWeatherResponse.model().windDir())
                .hasFieldOrPropertyWithValue(WeatherModel.Fields.pressureInMm, exceptedWeatherResponse.model().pressureInMm());

        verify(weatherRepository).getWeather(weatherRequest.longitude(),
                weatherRequest.latitude(),
                weatherRequest.limit());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-100.0, 2.0",
            "100.0, 2.0",
            "2.0, 100.0",
            "2.0, -100.0",
            "-100.0, -100.0",
            "100.0, 100.0"
    }, ignoreLeadingAndTrailingWhitespace = false)
    void getWeatherShouldReturnWeatherResponseDtoWhenIncorrectRequest(Double longitude, Double latitude) {
        // given
        WeatherRequest weatherRequest = WeatherRequestTestData.builder()
                .withLongitude(longitude)
                .withLatitude(latitude)
                .build().buildWeatherRequest();
        Optional<YandexResponse> yandexResponse = Optional.empty();

        doReturn(yandexResponse)
                .when(weatherRepository).getWeather(
                        weatherRequest.longitude(),
                        weatherRequest.latitude(),
                        weatherRequest.limit()
                );

        // when - then
        assertThrows(IncorrectDataOfWeather.class, () -> weatherService.getWeather(weatherRequest));

        verify(weatherRepository).getWeather(weatherRequest.longitude(),
                weatherRequest.latitude(),
                weatherRequest.limit());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 7, 10})
    void getForecastWeatherShouldReturnForecastForSomeDays(int countDays) {
        // given
        WeatherRequest weatherRequest = WeatherRequestTestData.builder()
                .withLimit(countDays)
                .build().buildWeatherRequest();
        YandexResponse yandexResponse = YandexResponseTestData.builder().build().buildYandexResponse();
        List<ForecastWeatherResponseDto> exceptedForecastResponse = ForecastModelTestData.builder()
                .build().buildListForecastWeatherResponseDto(countDays);

        doReturn(Optional.of(yandexResponse))
                .when(weatherRepository).getWeather(
                        weatherRequest.longitude(),
                        weatherRequest.latitude(),
                        weatherRequest.limit()
                );

        // when
        List<ForecastWeatherResponseDto> actualForecastResponse = weatherService.getForecastWeather(weatherRequest);

        // then
        IntStream.range(0, actualForecastResponse.size())
                .forEach((i) -> {
                    assertThat(actualForecastResponse.get(i)
                            .forecastOfDay()
                            .get("day"))
                            .isEqualTo(exceptedForecastResponse.get(i)
                                    .forecastOfDay()
                                    .get("day"));

                    assertThat(actualForecastResponse.get(i)
                            .forecastOfDay()
                            .get("morning"))
                            .isEqualTo(exceptedForecastResponse.get(i)
                                    .forecastOfDay()
                                    .get("morning"));

                    assertThat(actualForecastResponse.get(i)
                            .forecastOfDay()
                            .get("evening"))
                            .isEqualTo(exceptedForecastResponse.get(i)
                                    .forecastOfDay()
                                    .get("evening"));

                    assertThat(actualForecastResponse.get(i)
                            .forecastOfDay()
                            .get("night"))
                            .isEqualTo(exceptedForecastResponse.get(i)
                                    .forecastOfDay()
                                    .get("night"));
                });

        verify(weatherRepository).
                getWeather(weatherRequest.longitude(),
                        weatherRequest.latitude(),
                        weatherRequest.limit());
    }

    @Test
    void getWeathersShouldReturnWeatherInTwoCities() {
        // given
        WeatherRequest weatherRequestInSomeCity = WeatherRequestTestData.builder().build().buildWeatherRequest();
        YandexResponse expectedResponseInSomeCity = YandexResponseTestData.builder().build().buildYandexResponse();

        WeatherRequest weatherRequestInLondon = WeatherRequestTestData.builder()
                .withLatitude(Location.LONDON_LATITUDE.getCoord())
                .withLongitude(Location.LONDON_LONGITUDE.getCoord())
                .build().buildWeatherRequest();
        YandexResponse expectedResponseInLondon = YandexResponseTestData.builder().build().buildYandexResponse();

        List<YandexResponse> expectedForecast = List.of(expectedResponseInSomeCity, expectedResponseInLondon);
        List<WeatherResponseDto> exceptedWeatherResponse = expectedForecast.stream()
                .map(YandexResponseMapper::toWeatherResponseDto)
                .toList();

        doReturn(expectedForecast)
                .when(weatherRepository).getWeathers(
                        weatherRequestInSomeCity.longitude(),
                        weatherRequestInSomeCity.latitude(),
                        weatherRequestInSomeCity.limit()
                );

        // when
        List<WeatherResponseDto> actualWeatherResponse = weatherService.getWeathers(weatherRequestInSomeCity);

        // then
        assertEquals(exceptedWeatherResponse, actualWeatherResponse);
        assertEquals(2, actualWeatherResponse.size());

        IntStream.range(0, actualWeatherResponse.size())
                .forEach((i) ->
                        assertThat(actualWeatherResponse.get(i))
                                .hasFieldOrPropertyWithValue(WeatherResponseDto.Fields.date, exceptedWeatherResponse.get(i).date()));
        IntStream.range(0, actualWeatherResponse.size())
                .forEach((i) ->
                        assertThat(actualWeatherResponse.get(i).model())
                                .hasFieldOrPropertyWithValue(WeatherModel.Fields.temperature, exceptedWeatherResponse.get(i).model().temperature())
                                .hasFieldOrPropertyWithValue(WeatherModel.Fields.feelsTemperature, exceptedWeatherResponse.get(i).model().feelsTemperature())
                                .hasFieldOrPropertyWithValue(WeatherModel.Fields.condition, exceptedWeatherResponse.get(i).model().condition())
                                .hasFieldOrPropertyWithValue(WeatherModel.Fields.windSpeed, exceptedWeatherResponse.get(i).model().windSpeed())
                                .hasFieldOrPropertyWithValue(WeatherModel.Fields.windGust, exceptedWeatherResponse.get(i).model().windGust())
                                .hasFieldOrPropertyWithValue(WeatherModel.Fields.windDir, exceptedWeatherResponse.get(i).model().windDir())
                                .hasFieldOrPropertyWithValue(WeatherModel.Fields.pressureInMm, exceptedWeatherResponse.get(i).model().pressureInMm()));

        verify(weatherRepository).getWeathers(weatherRequestInSomeCity.longitude(),
                weatherRequestInSomeCity.latitude(),
                weatherRequestInSomeCity.limit());
    }
}
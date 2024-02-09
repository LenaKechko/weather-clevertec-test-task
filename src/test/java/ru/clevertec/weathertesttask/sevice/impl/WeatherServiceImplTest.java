package ru.clevertec.weathertesttask.sevice.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.weathertesttask.data.WeatherRequestTestData;
import ru.clevertec.weathertesttask.data.WeatherResponseTestData;
import ru.clevertec.weathertesttask.data.YandexResponseTestData;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
import ru.clevertec.weathertesttask.entity.YandexResponse;
import ru.clevertec.weathertesttask.model.WeatherRequest;
import ru.clevertec.weathertesttask.repository.impl.WeatherRepository;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;

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

//        doReturn(exceptedWeatherResponse)
//                .when(weatherService).getWeather(weatherRequest);

        // when
        WeatherResponseDto actualWeatherResponse = weatherService.getWeather(weatherRequest);

        // then
        System.out.println(exceptedWeatherResponse);
        System.out.println(actualWeatherResponse);
//        assertThat(actualWeatherResponse).
    }

    @Test
    void getForecastWeather() {
    }
}
package ru.clevertec.weathertesttask.repository.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.weathertesttask.data.WeatherRequestTestData;
import ru.clevertec.weathertesttask.data.YandexResponseTestData;
import ru.clevertec.weathertesttask.entity.IYandexResponse;
import ru.clevertec.weathertesttask.entity.YandexResponse;
import ru.clevertec.weathertesttask.model.WeatherRequest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WeatherRepositoryTest {

    @Mock
    private IYandexResponse yandexResponse;

    @InjectMocks
    private WeatherRepository weatherRepository;

    @Test
    void getWeatherShouldReturnResponse() {
        // given
        WeatherRequest weatherRequest = WeatherRequestTestData.buildWeatherRequest();
        YandexResponse expected = YandexResponseTestData.builder().build().buildYandexResponse();

        doReturn(expected)
                .when(yandexResponse).getWeather(weatherRequest.longitude(),
                        weatherRequest.latitude(),
                        weatherRequest.limit());

        // when
        Optional<YandexResponse> actual = weatherRepository.getWeather(weatherRequest.longitude(),
                weatherRequest.latitude(),
                weatherRequest.limit());

        // then
        assertEquals(Optional.of(expected), actual);
        verify(yandexResponse).getWeather(weatherRequest.longitude(),
                weatherRequest.latitude(),
                weatherRequest.limit());
    }

    @Test
    void getWeatherShouldNotReturnResponse() {
        // given

        // when
        Optional<YandexResponse> actual = weatherRepository.getWeather(0.0, 0.0, 0);

        // then
        assertEquals(Optional.empty(), actual);
        verify(yandexResponse).getWeather(0.0, 0.0, 0);
    }
}
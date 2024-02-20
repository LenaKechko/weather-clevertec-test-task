package ru.clevertec.weathertesttask.repository.impl;

import feign.FeignException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.weathertesttask.constant.Location;
import ru.clevertec.weathertesttask.data.WeatherRequestTestData;
import ru.clevertec.weathertesttask.data.YandexResponseTestData;
import ru.clevertec.weathertesttask.entity.IYandexResponse;
import ru.clevertec.weathertesttask.entity.YandexResponse;
import ru.clevertec.weathertesttask.model.WeatherRequest;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WeatherRepositoryTest {

    @Mock
    private IYandexResponse yandexResponse;

    @InjectMocks
    private WeatherRepository weatherRepository;

    @Test
    @DisplayName("Возвращает погоду по запросу")
    void getWeatherShouldReturnResponse() {
        // given
        WeatherRequest weatherRequest = WeatherRequestTestData.builder().build().buildWeatherRequest();
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
    @DisplayName("Не возвращает погоду из-за не корректных данных от пользователя")
    void getWeatherShouldNotReturnResponse() {
        // given
        WeatherRequest weatherRequest = WeatherRequestTestData.builder()
                .withLatitude(-100.0)
                .build().buildWeatherRequest();

        doThrow(FeignException.FeignClientException.class)
                .when(yandexResponse).getWeather(weatherRequest.longitude(),
                        weatherRequest.latitude(),
                        weatherRequest.limit());

        // when
        Optional<YandexResponse> actual = weatherRepository.getWeather(weatherRequest.longitude(),
                weatherRequest.latitude(),
                weatherRequest.limit());

        // then
        assertEquals(Optional.empty(), actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 5, 10, 100})
    @DisplayName("Возвращает погоду, когда делается несколько запросов")
    void getWeatherShouldReturnResponse_WhenWeDoMuchRequest(int countRequest) {
        // given
        WeatherRequest weatherRequest = WeatherRequestTestData.builder().build().buildWeatherRequest();
        YandexResponse expected = YandexResponseTestData.builder().build().buildYandexResponse();

        doReturn(expected)
                .when(yandexResponse).getWeather(weatherRequest.longitude(),
                        weatherRequest.latitude(),
                        weatherRequest.limit());


        // when
        CompletableFuture<Optional<YandexResponse>> testFuture1 = CompletableFuture.supplyAsync(() ->
                weatherRepository.getWeather(weatherRequest.longitude(),
                        weatherRequest.latitude(),
                        weatherRequest.limit())
        );

        // then
        IntStream.range(0, countRequest)
                .mapToObj((i) -> testFuture1)
                .map(CompletableFuture::join)
                .forEach((actual) -> assertEquals(Optional.of(expected), actual));
    }

    @Test
    @DisplayName("Возвращает погоду по корректному запросу и не возвращает, если запрос не корректен")
    void getWeatherShouldReturnResponseAndEmptyResponse_WhenWeHasCorrectRequestAndIncorrectRequest() {
        // given
        WeatherRequest correctWeatherRequest = WeatherRequestTestData.builder().build().buildWeatherRequest();
        WeatherRequest incorrectWeatherRequest = WeatherRequestTestData.builder()
                .withLatitude(-100.0)
                .build().buildWeatherRequest();
        YandexResponse expectedCorrectResponse = YandexResponseTestData.builder().build().buildYandexResponse();
        Optional<YandexResponse> expectedEmptyResponse = Optional.empty();

        lenient().doReturn(expectedCorrectResponse)
                .when(yandexResponse).getWeather(correctWeatherRequest.longitude(),
                        correctWeatherRequest.latitude(),
                        correctWeatherRequest.limit());

        lenient().doThrow(FeignException.FeignClientException.class)
                .when(yandexResponse).getWeather(incorrectWeatherRequest.longitude(),
                        incorrectWeatherRequest.latitude(),
                        incorrectWeatherRequest.limit());

        // when
        CompletableFuture<Optional<YandexResponse>> actualCorrectFuture = CompletableFuture.supplyAsync(() ->
                weatherRepository.getWeather(correctWeatherRequest.longitude(),
                        correctWeatherRequest.latitude(),
                        correctWeatherRequest.limit())
        );
        CompletableFuture<Optional<YandexResponse>> actualEmptyFuture = CompletableFuture.supplyAsync(() ->
                weatherRepository.getWeather(incorrectWeatherRequest.longitude(),
                        incorrectWeatherRequest.latitude(),
                        incorrectWeatherRequest.limit())
        );

        // then
        CompletableFuture.allOf(actualCorrectFuture, actualEmptyFuture);

        actualCorrectFuture.thenAccept(actual -> assertEquals(Optional.of(expectedCorrectResponse), actual));
        actualEmptyFuture.thenAccept(actual -> assertEquals(expectedEmptyResponse, actual));
    }

    @Test
    void getWeathersShouldReturnForecastForSomeCityAndLondon() {
        // given
        WeatherRequest weatherRequestInSomeCity = WeatherRequestTestData.builder().build().buildWeatherRequest();
        YandexResponse expectedResponseInSomeCity = YandexResponseTestData.builder().build().buildYandexResponse();

        doReturn(expectedResponseInSomeCity)
                .when(yandexResponse).getWeather(weatherRequestInSomeCity.longitude(),
                        weatherRequestInSomeCity.latitude(),
                        weatherRequestInSomeCity.limit());

        WeatherRequest weatherRequestInLondon = WeatherRequestTestData.builder()
                .withLatitude(Location.LONDON_LATITUDE.getCoord())
                .withLongitude(Location.LONDON_LONGITUDE.getCoord())
                .build().buildWeatherRequest();
        YandexResponse expectedResponseInLondon = YandexResponseTestData.builder().build().buildYandexResponse();

        doReturn(expectedResponseInLondon)
                .when(yandexResponse).getWeather(weatherRequestInLondon.longitude(),
                        weatherRequestInLondon.latitude(),
                        weatherRequestInLondon.limit());

        // when
        List<YandexResponse> actualResult = weatherRepository.getWeathers(weatherRequestInSomeCity.longitude(),
                weatherRequestInSomeCity.latitude(),
                weatherRequestInSomeCity.limit());

        // then
        assertEquals(2, actualResult.size());

    }
}
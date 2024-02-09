package ru.clevertec.weathertesttask.integration;
//
//
//
///* Доработать интеграционное тестирование используя WireMock!!!*/
//
//package ru.clevertec.weathertesttask.integration;
//
//import com.github.tomakehurst.wiremock.junit5.WireMockTest;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.Test;
//import org.skyscreamer.jsonassert.JSONAssert;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import ru.clevertec.weathertesttask.data.WeatherRequestTestData;
//import ru.clevertec.weathertesttask.entity.IYandexResponse;
//import ru.clevertec.weathertesttask.model.WeatherRequest;
//import ru.clevertec.weathertesttask.repository.impl.WeatherRepository;
//
//import static com.github.tomakehurst.wiremock.client.WireMock.*;
//import static org.assertj.core.api.Assertions.assertThatCode;
//
//
//@SpringBootTest
//@RequiredArgsConstructor
//@WireMockTest(httpPort = "https://api.weather.yandex.ru/v2/forecast")
//class WeatherRepositoryTest {
//
//    private final WeatherRepository weatherRepository;
//
//    @Test
//    void getWeather() {
//        // given
//        WeatherRequest weatherRequest = WeatherRequestTestData.buildWeatherRequest();
//
//        // when
//        stubFor(get("https://api.weather.yandex.ru/v2/forecast")
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withBodyFile("get-weather-response.json")
//                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)));
//
//        // then
//        String user = "{\"firstName\":\"Dmitriy\", \"secondName\":\"Steba\", \"age\":\"30\"}";
//        JSONAssert.assertEquals("{\"firstName\":\"Dmitriy\"}", user, false);
//        assertThatCode(() -> weatherRepository.getWeather(weatherRequest.longitude(),
//                weatherRequest.latitude(),
//                weatherRequest.limit()))
//                .doesNotThrowAnyException();
//    }
//}

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import ru.clevertec.weathertesttask.data.WeatherRequestTestData;
import ru.clevertec.weathertesttask.data.WeatherResponseTestData;
import ru.clevertec.weathertesttask.data.YandexResponseTestData;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
import ru.clevertec.weathertesttask.entity.YandexResponse;
import ru.clevertec.weathertesttask.model.WeatherRequest;
import ru.clevertec.weathertesttask.repository.impl.WeatherRepository;
import ru.clevertec.weathertesttask.sevice.impl.WeatherServiceImpl;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
@RequiredArgsConstructor
@WireMockTest
class WeatherServiceImplTest {

    @MockBean
    private final WeatherRepository weatherRepository;

    @Autowired
    private final WeatherServiceImpl weatherService;

    @Test
    void getWeatherShouldReturnWeatherResponseDtoWhenCorrectRequest() {
        WeatherRequest weatherRequest = WeatherRequestTestData.builder().build().buildWeatherRequest();
        YandexResponse yandexResponse = YandexResponseTestData.builder().build().buildYandexResponse();
        WeatherResponseDto exceptedWeatherResponse = WeatherResponseTestData.builder().build().buildWeatherDto();

//        String jsonString = TestData.toJsonString(accessRiskManagementResponseDto);

        stubFor(post("https://api.weather.yandex.ru/v2/forecast")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(String.valueOf(new ClassPathResource("get-weather-response.json")))
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)));

        assertThatCode(() -> weatherService.getWeather(weatherRequest))
                .doesNotThrowAnyException();
    }
}
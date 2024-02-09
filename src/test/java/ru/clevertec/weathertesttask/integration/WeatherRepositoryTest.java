//
//
//
///* Доработать интеграционное тестирование используя WireMock!!!*/
//
//
//
//
//
//
//
//
//
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
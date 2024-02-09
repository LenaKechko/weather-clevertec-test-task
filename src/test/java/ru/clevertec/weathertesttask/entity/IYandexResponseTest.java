package ru.clevertec.weathertesttask.entity;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import ru.clevertec.weathertesttask.data.WeatherRequestTestData;
import ru.clevertec.weathertesttask.model.WeatherRequest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RestClientTest(IYandexResponse.class)
@AutoConfigureWebClient(registerRestTemplate = true)
@RequiredArgsConstructor
        /*передать не корректные данные и посмотреть чтобы вернулся exception
         * FeignServerException*/
class IYandexResponseTest {

    @Autowired
    private final IYandexResponse iYandexResponse;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Test
    public void getWeatherShouldReturnJsonWithWeather() {
        // given
        WeatherRequest weatherRequest = WeatherRequestTestData.builder().build().buildWeatherRequest();
//        YandexResponse expectedYandexResponse = YandexResponseTestData.builder().build().buildYandexResponse();
        this.mockRestServiceServer.expect(
                        MockRestRequestMatchers.requestTo("https://api.weather.yandex.ru/v2/forecast"))
                .andRespond(MockRestResponseCreators
                        .withSuccess(new ClassPathResource("get-weather-response.json"),
                                MediaType.APPLICATION_JSON));

        YandexResponse actualYandexResponse = iYandexResponse.getWeather(weatherRequest.longitude(),
                weatherRequest.latitude(),
                weatherRequest.limit());


        assertNotNull(actualYandexResponse);

    }


}

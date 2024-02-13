package ru.clevertec.weathertesttask.entity;

import com.github.tomakehurst.wiremock.client.WireMock;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import ru.clevertec.weathertesttask.config.MongoContainerInitializer;
import ru.clevertec.weathertesttask.data.Constants;
import wiremock.org.eclipse.jetty.http.HttpHeader;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9561)
@ActiveProfiles("test")
class IYandexResponseTest extends MongoContainerInitializer {

    @Autowired
    private IYandexResponse iYandexResponse;

    @Test
    public void getWeatherShouldReturnJsonWithWeather() {
        // given
        WireMock.givenThat(WireMock.get(WireMock.urlPathEqualTo("/"))
                .withQueryParam("lon", equalTo(Constants.LONGITUDE.toString()))
                .withQueryParam("lat", equalTo(Constants.LATITUDE.toString()))
                .withQueryParam("limit", equalTo(Constants.LIMIT.toString()))
                .willReturn(aResponse()
                        .withHeader(HttpHeader.CONTENT_TYPE.asString(), MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("yandex-response-successful.json")
                        .withStatus(HttpStatus.OK.value())));

        // when
        YandexResponse actualYandexResponse = iYandexResponse.getWeather(Constants.LONGITUDE, Constants.LATITUDE, Constants.LIMIT);

        // then
        Assertions.assertNotNull(actualYandexResponse);
    }

    @Test
    public void getWeatherShouldReturnFeignServerException() {
        // given
        double longitude = -100.0;
        double latitude = 100.0;
        WireMock.givenThat(WireMock.get(WireMock.urlPathEqualTo("/"))
                .withQueryParam("lon", equalTo(String.valueOf(longitude)))
                .withQueryParam("lat", equalTo(String.valueOf(latitude)))
                .withQueryParam("limit", equalTo(Constants.LIMIT.toString()))
                .willReturn(aResponse()
                        .withHeader(HttpHeader.CONTENT_TYPE.asString(), MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())));

        // when - then
        Assertions.assertThrows(FeignException.FeignServerException.class,
                () -> iYandexResponse.getWeather(longitude, latitude, Constants.LIMIT));
    }
}

package ru.clevertec.weathertesttask.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class WeatherMock {

//    public static void setupMockWeatherResponse(WireMockServer mockService) throws IOException {
//        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("?lat=30.9754&lon=52.4345"))
//                .willReturn(WireMock.aResponse()
//                        .withStatus(HttpStatus.OK.value())
//                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                        .withBody(
//                                StreamUtils.copyToString(
//                                        WeatherMock.class.getClassLoader().getResourceAsStream("get-weather-response.json"),
//                                        Charset.defaultCharset()))));
//    }
}

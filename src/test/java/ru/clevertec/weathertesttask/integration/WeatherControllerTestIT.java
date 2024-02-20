package ru.clevertec.weathertesttask.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.clevertec.weathertesttask.config.MongoContainerInitializer;
import ru.clevertec.weathertesttask.data.Constants;
import ru.clevertec.weathertesttask.data.WeatherRequestTestData;
import ru.clevertec.weathertesttask.dto.ForecastWeatherResponseDto;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
import ru.clevertec.weathertesttask.entity.YandexResponse;
import ru.clevertec.weathertesttask.exception.IncorrectDataOfWeather;
import ru.clevertec.weathertesttask.mapper.ForecastModelMapper;
import ru.clevertec.weathertesttask.mapper.YandexResponseMapper;
import ru.clevertec.weathertesttask.model.WeatherRequest;
import ru.clevertec.weathertesttask.sevice.impl.WeatherServiceImpl;
import wiremock.org.eclipse.jetty.http.HttpHeader;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureWireMock(port = 9561)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WeatherControllerTestIT extends MongoContainerInitializer {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WeatherServiceImpl weatherService;

    @Autowired
    private Gson gson;

    ObjectMapper objectMapper = new ObjectMapper()
            .findAndRegisterModules()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @Test
    @SneakyThrows
    void getWeatherShouldReturnForecastInExactCity() {
        WeatherRequest weatherRequest = WeatherRequestTestData.builder().build().buildWeatherRequest();

        String jsonString = FileUtils.readFileToString(new ClassPathResource("__files/yandex-response-successful.json").getFile(),
                StandardCharsets.UTF_8);
        YandexResponse yandexResponse = objectMapper.readValue(jsonString, YandexResponse.class);

        WeatherResponseDto exceptedWeatherResponse = YandexResponseMapper.toWeatherResponseDto(yandexResponse);

        givenThat(get(WireMock.urlPathEqualTo("/"))
                .withQueryParam("lon", equalTo(Constants.LONGITUDE.toString()))
                .withQueryParam("lat", equalTo(Constants.LATITUDE.toString()))
                .withQueryParam("limit", equalTo(Constants.LIMIT.toString()))
                .willReturn(aResponse()
                        .withHeader(HttpHeader.CONTENT_TYPE.asString(), MediaType.APPLICATION_JSON_VALUE)
                        .withBody(jsonString)
                        .withStatus(HttpStatus.OK.value())
                ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpectAll(status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON_VALUE),
                        jsonPath("$.now.temp").value(exceptedWeatherResponse.model().temperature()),
                        jsonPath("$.now.condition").value(exceptedWeatherResponse.model().condition()),
                        jsonPath("$.now.feels_like").value(exceptedWeatherResponse.model().feelsTemperature()))
                .andDo(MockMvcResultHandlers.print());

        assertThatCode(() -> weatherService.getWeather(weatherRequest))
                .doesNotThrowAnyException();
    }

    @SneakyThrows
    @Test
    void getWeatherShouldReturnForecastForSomeDaysInSomeCityWhenCorrectRequest() {
        int countDays = 2;
        WeatherRequest weatherRequest = WeatherRequestTestData.builder()
                .withLimit(countDays)
                .build().buildWeatherRequest();

        String jsonString = FileUtils.readFileToString(new ClassPathResource("__files/yandex-response-successful.json").getFile(),
                StandardCharsets.UTF_8);
        YandexResponse yandexResponse = objectMapper.readValue(jsonString, YandexResponse.class);

        List<ForecastWeatherResponseDto> forecastWeatherResponseDtoList = yandexResponse.forecast().stream()
                .map(ForecastModelMapper::toForecastWeatherResponseDto)
                .toList();

        givenThat(get(WireMock.urlPathEqualTo("/"))
                .withQueryParam("lon", equalTo(weatherRequest.longitude().toString()))
                .withQueryParam("lat", equalTo(weatherRequest.latitude().toString()))
                .withQueryParam("limit", equalTo(weatherRequest.limit().toString()))
                .willReturn(aResponse()
                        .withHeader(HttpHeader.CONTENT_TYPE.asString(), MediaType.APPLICATION_JSON_VALUE)
                        .withBody(jsonString)
                        .withStatus(HttpStatus.OK.value())
                ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/days/{countDays}", countDays)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpectAll(status().isOk(),
                        jsonPath("$.[:1].date").value(forecastWeatherResponseDtoList.get(0).date().toString()),
                        jsonPath("$.[:2].date").value(forecastWeatherResponseDtoList.get(1).date().toString()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @SneakyThrows
    void getWeatherShouldReturnWeatherNowInSomeCityWhenCorrectRequest() {
        WeatherRequest weatherRequest = WeatherRequestTestData.builder().build().buildWeatherRequest();

        String jsonString = FileUtils.readFileToString(new ClassPathResource("__files/yandex-response-successful.json").getFile(),
                StandardCharsets.UTF_8);
        YandexResponse yandexResponse = objectMapper.readValue(jsonString, YandexResponse.class);

        WeatherResponseDto exceptedWeatherResponse = YandexResponseMapper.toWeatherResponseDto(yandexResponse);

        givenThat(get(WireMock.urlPathEqualTo("/"))
                .withQueryParam("lon", equalTo(weatherRequest.longitude().toString()))
                .withQueryParam("lat", equalTo(weatherRequest.latitude().toString()))
                .withQueryParam("limit", equalTo(weatherRequest.limit().toString()))
                .willReturn(aResponse()
                        .withHeader(HttpHeader.CONTENT_TYPE.asString(), MediaType.APPLICATION_JSON_VALUE)
                        .withBody(jsonString)
                        .withStatus(HttpStatus.OK.value())
                ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/city")
                        .content(gson.toJson(weatherRequest))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpectAll(status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON_VALUE),
                        jsonPath("$.now.temp").value(exceptedWeatherResponse.model().temperature()),
                        jsonPath("$.now.condition").value(exceptedWeatherResponse.model().condition()),
                        jsonPath("$.now.feels_like").value(exceptedWeatherResponse.model().feelsTemperature()))
                .andDo(MockMvcResultHandlers.print());

        assertThatCode(() -> weatherService.getWeather(weatherRequest))
                .doesNotThrowAnyException();
    }

    @Test
    @SneakyThrows
    void getWeatherShouldReturnExceptionWhenIncorrectRequest() {
        WeatherRequest weatherRequest = WeatherRequestTestData.builder()
                .withLongitude(100.0)
                .withLatitude(100.0)
                .build().buildWeatherRequest();
        String expectedHeader = new IncorrectDataOfWeather(weatherRequest.longitude(), weatherRequest.latitude())
                .getMessage();

        stubFor(get(WireMock.urlPathEqualTo("/"))
                .withQueryParam("lon", equalTo(weatherRequest.longitude().toString()))
                .withQueryParam("lat", equalTo(weatherRequest.latitude().toString()))
                .withQueryParam("limit", equalTo(weatherRequest.limit().toString()))
                .willReturn(aResponse()
                        .withHeader(HttpHeader.CONTENT_TYPE.asString(), MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(HttpStatus.BAD_REQUEST.value())
                ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/city")
                        .content(gson.toJson(weatherRequest))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Error message", expectedHeader))
                .andDo(MockMvcResultHandlers.print());
    }

}

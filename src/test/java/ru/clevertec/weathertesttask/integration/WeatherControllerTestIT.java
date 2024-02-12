package ru.clevertec.weathertesttask.integration;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.clevertec.weathertesttask.config.LocalDateTypeAdapter;
import ru.clevertec.weathertesttask.config.MongoContainerInitializer;
import ru.clevertec.weathertesttask.config.ZonedDateTimeTypeAdapter;
import ru.clevertec.weathertesttask.data.*;
import ru.clevertec.weathertesttask.dto.ForecastWeatherResponseDto;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
import ru.clevertec.weathertesttask.entity.YandexResponse;
import ru.clevertec.weathertesttask.exception.IncorrectDataOfWeather;
import ru.clevertec.weathertesttask.model.WeatherRequest;
import ru.clevertec.weathertesttask.sevice.impl.WeatherServiceImpl;
import wiremock.org.eclipse.jetty.http.HttpHeader;

import java.time.LocalDate;
import java.time.ZonedDateTime;
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

    //    @Autowired
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeTypeAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
            .create();

    @Test
    @SneakyThrows
    void getWeatherShouldReturnForecastInExactCity() {
        WeatherRequest weatherRequest = WeatherRequestTestData.builder().build().buildWeatherRequest();

//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        YandexResponse ben = objectMapper.readValue(new ClassPathResource("__files/yandex-response-successful.json").getFile(),
//                YandexResponse.class);


        // считать yandexResponse с файла
        YandexResponse yandexResponse = YandexResponseTestData.builder().build().buildYandexResponse();
        WeatherResponseDto exceptedWeatherResponse = WeatherResponseTestData.builder().build().buildWeatherDto();

        givenThat(get(WireMock.urlPathEqualTo("/"))
                .withQueryParam("lon", equalTo(Constants.LONGITUDE.toString()))
                .withQueryParam("lat", equalTo(Constants.LATITUDE.toString()))
                .withQueryParam("limit", equalTo(Constants.LIMIT.toString()))
                .willReturn(aResponse()
                        .withHeader(HttpHeader.CONTENT_TYPE.asString(), MediaType.APPLICATION_JSON_VALUE)
//                        .withBodyFile("yandex-response-successful.json")
                        .withBody(gson.toJson(yandexResponse))
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
//!!!
    @SneakyThrows
    @Test
    void getWeatherShouldReturnForecastForSomeDaysInSomeCityWhenCorrectRequest() {
        int countDays = 2;
        WeatherRequest weatherRequest = WeatherRequestTestData.builder()
                .withLimit(countDays)
                .build().buildWeatherRequest();
        List<ForecastWeatherResponseDto> forecastWeatherResponseDtoList = ForecastModelTestData.builder()
                .build().buildListForecastWeatherResponseDto(countDays);

        givenThat(get(WireMock.urlPathEqualTo("/"))
                .withQueryParam("lon", equalTo(weatherRequest.longitude().toString()))
                .withQueryParam("lat", equalTo(weatherRequest.latitude().toString()))
                .withQueryParam("limit", equalTo(weatherRequest.limit().toString()))
                .willReturn(aResponse()
                                .withHeader(HttpHeader.CONTENT_TYPE.asString(), MediaType.APPLICATION_JSON_VALUE)
                                .withBodyFile("yandex-response-successful.json")
//                        .withBody(gson.toJson(forecastWeatherResponseDtoList))
                                .withStatus(HttpStatus.OK.value())
                ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/days/{countDays}", countDays)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    //!!!!!!
    @Test
    @SneakyThrows
    void getWeatherShouldReturnWeatherNowInSomeCityWhenCorrectRequest() {
        WeatherRequest weatherRequest = WeatherRequestTestData.builder().build().buildWeatherRequest();
        YandexResponse yandexResponse = YandexResponseTestData.builder().build().buildYandexResponse();
        WeatherResponseDto exceptedWeatherResponse = WeatherResponseTestData.builder().build().buildWeatherDto();

        givenThat(get(WireMock.urlPathEqualTo("/"))
                .withQueryParam("lon", equalTo(weatherRequest.longitude().toString()))
                .withQueryParam("lat", equalTo(weatherRequest.latitude().toString()))
                .withQueryParam("limit", equalTo(weatherRequest.limit().toString()))
                .willReturn(aResponse()
                        .withHeader(HttpHeader.CONTENT_TYPE.asString(), MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("yandex-response-successful.json")
//                        .withBody(gson.toJson(yandexResponse))
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

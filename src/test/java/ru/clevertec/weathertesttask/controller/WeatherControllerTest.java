package ru.clevertec.weathertesttask.controller;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ru.clevertec.weathertesttask.data.ForecastModelTestData;
import ru.clevertec.weathertesttask.data.WeatherRequestTestData;
import ru.clevertec.weathertesttask.data.WeatherResponseTestData;
import ru.clevertec.weathertesttask.dto.ForecastWeatherResponseDto;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
import ru.clevertec.weathertesttask.exception.IncorrectDataOfWeather;
import ru.clevertec.weathertesttask.model.WeatherRequest;
import ru.clevertec.weathertesttask.sevice.impl.WeatherServiceImpl;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherController.class)
@AutoConfigureMockMvc
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherServiceImpl weatherService;

    @Autowired
    private Gson gson;

    @Test
    @SneakyThrows
    void getWeatherInExactCityShouldReturnWeatherNowInExactCity() {
        // given
        WeatherRequest weatherRequest = WeatherRequestTestData.builder().build().buildWeatherRequest();
        WeatherResponseDto weatherResponseDto = WeatherResponseTestData.builder()
                .build().buildWeatherDto();

        doReturn(weatherResponseDto)
                .when(weatherService).getWeather(weatherRequest);

        // when - then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpectAll(status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON_VALUE),
                        jsonPath("$.now.temp").value(weatherResponseDto.model().temperature()),
                        jsonPath("$.now.condition").value(weatherResponseDto.model().condition()),
                        jsonPath("$.now.feels_like").value(weatherResponseDto.model().feelsTemperature()))
                .andDo(MockMvcResultHandlers.print());
    }

    @SneakyThrows
    @ParameterizedTest
    @ValueSource(ints = {2, 7, 10})
    void getWeatherForSomeDaysInExactCityShouldReturnForecast(int countDays) {
        // given
        WeatherRequest weatherRequest = WeatherRequestTestData.builder()
                .withLimit(countDays).build().buildWeatherRequest();
        List<ForecastWeatherResponseDto> forecastWeatherResponseDtoList = ForecastModelTestData.builder()
                .build().buildListForecastWeatherResponseDto(countDays);

        // when
        doReturn(forecastWeatherResponseDtoList)
                .when(weatherService).getForecastWeather(weatherRequest);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/days/{countDays}", countDays)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @SneakyThrows
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void getWeatherForSomeDaysInExactCityShouldReturnException(int countDays) {
        // given

        // when - then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/days/{countDays}", countDays)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @SneakyThrows
    void getWeatherInSomeCityShouldReturnWeatherNowInSomeCity() {
        WeatherRequest weatherRequest = WeatherRequestTestData.builder().build().buildWeatherRequest();
        WeatherResponseDto weatherResponseDto = WeatherResponseTestData.builder()
                .build().buildWeatherDto();

        doReturn(weatherResponseDto)
                .when(weatherService).getWeather(weatherRequest);

        // when - then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/city")
                        .content(gson.toJson(weatherRequest))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpectAll(status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON_VALUE),
                        jsonPath("$.now.temp").value(weatherResponseDto.model().temperature()),
                        jsonPath("$.now.condition").value(weatherResponseDto.model().condition()),
                        jsonPath("$.now.feels_like").value(weatherResponseDto.model().feelsTemperature()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @SneakyThrows
    void getWeatherInSomeCityShouldReturnException() {
        // given
        WeatherRequest weatherRequest = WeatherRequestTestData.builder()
                .withLatitude(100.0)
                .withLongitude(100.0)
                .build().buildWeatherRequest();
        String expectedHeader = new IncorrectDataOfWeather(weatherRequest.longitude(), weatherRequest.latitude())
                .getMessage();

        // when
        doThrow(IncorrectDataOfWeather.class)
                .when(weatherService).getWeather(weatherRequest);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/city")
                        .content(gson.toJson(weatherRequest))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
//                .andExpect(header().string("Error message", expectedHeader))
                .andDo(MockMvcResultHandlers.print());
    }
}
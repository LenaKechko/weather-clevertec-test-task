package ru.clevertec.weathertesttask.sevice.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.weathertesttask.dto.ForecastWeatherResponseDto;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
import ru.clevertec.weathertesttask.entity.YandexResponse;
import ru.clevertec.weathertesttask.entity.model.WeatherModel;
import ru.clevertec.weathertesttask.exception.IncorrectDataOfWeather;
import ru.clevertec.weathertesttask.model.WeatherRequest;
import ru.clevertec.weathertesttask.repository.impl.WeatherRepository;
import ru.clevertec.weathertesttask.sevice.WeatherService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    @Override
    public WeatherResponseDto getWeather(WeatherRequest request) {
        System.out.println("I am service");
        return weatherRepository.getWeather(request.longitude(), request.latitude(), request.limit())
                .map(response -> new WeatherResponseDto(
                        response.date().withZoneSameInstant(response.info().tzInfo().nameTimeZone()),
                        new WeatherModel(response.model().temperature(),
                                response.model().feelsTemperature(),
                                response.model().condition(),
                                response.model().windSpeed(),
                                response.model().windGust(),
                                response.model().windDir(),
                                response.model().pressureInMm()
                        )
                ))
                .orElseThrow(() -> new IncorrectDataOfWeather(request.longitude(), request.latitude()));
    }

    @Override
    public List<ForecastWeatherResponseDto> getForecastWeather(WeatherRequest request) {
        System.out.println("I am service");
        System.out.println(weatherRepository.getWeather(request.longitude(), request.latitude(), request.limit()));
        return weatherRepository.getWeather(request.longitude(), request.latitude(), request.limit())
                .stream()
                .map(YandexResponse::forecast)
                .flatMap(Collection::stream)
                .map(this::createForecastWeatherResponseDto)
                .toList();
    }

    private ForecastWeatherResponseDto createForecastWeatherResponseDto(YandexResponse.ForecastModel model) {
        return new ForecastWeatherResponseDto(model.date(),
                Map.of("morning", model.partOfDayList().morning(),
                        "day", model.partOfDayList().day(),
                        "evening", model.partOfDayList().evening(),
                        "night", model.partOfDayList().night()));
    }

}

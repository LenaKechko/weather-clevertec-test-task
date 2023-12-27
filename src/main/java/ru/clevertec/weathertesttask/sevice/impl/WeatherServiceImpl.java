package ru.clevertec.weathertesttask.sevice.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import ru.clevertec.weathertesttask.entity.model.WeatherModel;
import ru.clevertec.weathertesttask.exception.IncorrectDataOfWeather;
import ru.clevertec.weathertesttask.model.WeatherRequest;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
import ru.clevertec.weathertesttask.repository.impl.WeatherRepository;
import ru.clevertec.weathertesttask.sevice.WeatherService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    @Override
    public WeatherResponseDto getWeather(WeatherRequest request) {
        System.out.println("I am service");
        return weatherRepository.getWeather(request.longitude(), request.latitude(), request.limit())
                .map(response -> new WeatherResponseDto(
                        response.date(),
                        response.nameLocation(),
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
}

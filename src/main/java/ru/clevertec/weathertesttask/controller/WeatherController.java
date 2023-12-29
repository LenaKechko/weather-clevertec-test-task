package ru.clevertec.weathertesttask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.weathertesttask.constant.Location;
import ru.clevertec.weathertesttask.exception.IncorrectDataOfWeather;
import ru.clevertec.weathertesttask.model.WeatherRequest;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
import ru.clevertec.weathertesttask.sevice.WeatherService;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {

    @Autowired
    private final WeatherService weatherService;

    @GetMapping
    public ResponseEntity<WeatherResponseDto> getWeatherInExactCity() {
        WeatherRequest request = new WeatherRequest(Location.GOMEL_LONGITUDE.getCoord(), Location.GOMEL_LATITUDE.getCoord(), 1);
        System.out.println("I am controller");
        return getWeather(request);
    }

    @GetMapping("/days/{countDays}")
    public ResponseEntity<WeatherResponseDto> getWeatherForSomeDaysInExactCity(@PathVariable Integer countDays) {
        WeatherRequest request = new WeatherRequest(Location.GOMEL_LONGITUDE.getCoord(), Location.GOMEL_LATITUDE.getCoord(), countDays);
        System.out.println("I am controller");
        return getWeather(request);
    }

    @GetMapping("/city")
    public ResponseEntity<WeatherResponseDto> getWeatherInSomeCity(@RequestBody WeatherRequest request) {
        System.out.println("I am controller");
        return getWeather(request);
    }

    private ResponseEntity<WeatherResponseDto> getWeather(WeatherRequest request) {
        try {
            return ResponseEntity.ok(weatherService.getWeather(request));
        } catch (IncorrectDataOfWeather e) {
            return ResponseEntity.notFound().header(e.getMessage()).build();
        }
    }

}

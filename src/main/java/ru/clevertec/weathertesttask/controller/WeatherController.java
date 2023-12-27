package ru.clevertec.weathertesttask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.weathertesttask.exception.IncorrectDataOfWeather;
import ru.clevertec.weathertesttask.model.WeatherRequest;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
import ru.clevertec.weathertesttask.sevice.WeatherService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WeatherController {

    @Autowired
    private final WeatherService weatherService;

    @PostMapping("/weather")
    public ResponseEntity<WeatherResponseDto> getWeather(@RequestBody WeatherRequest request) {
        System.out.println("I am controller");
        try {
            return ResponseEntity.ok(weatherService.getWeather(request));
        } catch (IncorrectDataOfWeather e) {
            return ResponseEntity.notFound().header(e.getMessage()).build();
        }
    }

//    @PostMapping("/limit/{limit}")
//    public WeatherResponse getWeather(@RequestBody WeatherRequest request) {
//        return weatherService.getWeather(request);
//    }

}

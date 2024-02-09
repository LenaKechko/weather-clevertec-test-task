package ru.clevertec.weathertesttask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.weathertesttask.constant.Location;
import ru.clevertec.weathertesttask.dto.ForecastWeatherResponseDto;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
import ru.clevertec.weathertesttask.exception.IncorrectDataOfWeather;
import ru.clevertec.weathertesttask.model.WeatherRequest;
import ru.clevertec.weathertesttask.sevice.WeatherService;

import java.util.List;

/**
 * Класс для работы с Rest-запросами с префиксом /api/weather
 * Аннотация RequiredArgsConstructor заменяет написание конструктора со всеми полями класса
 */

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {

    /**
     * Поле для подключения слоя сервиса,
     * который является реализацией интерфейса WeatherService.
     * Для внеднения используется аннотация Autowired
     */

    private final WeatherService weatherService;

    /**
     * Метод обрабатывающий Rest-запросы с префиксом /api/weather.
     * Возвращает текущую погоду в городе Гомель
     *
     * @return json определенный моделью WeatherResponseDto
     */
    @GetMapping
    public ResponseEntity<WeatherResponseDto> getWeatherInExactCity() {
        WeatherRequest request = new WeatherRequest(Location.GOMEL_LONGITUDE.getCoord(), Location.GOMEL_LATITUDE.getCoord(), 1);
        return getWeather(request);
    }

    /**
     * Метод обрабатывающий Rest-запросы с префиксом /api/weather/days/{количество}.
     * Возвращает погоду на указанное в запросе количество дней в городе Гомель.
     * Максимальное число дней - 7, при вводе большего числа, будет возвращать прогноз на 7 дней.
     *
     * @param countDays параметр количества дней, вводится пользователем в адресной строке
     * @return json, состоящий из List типа ForecastWeatherResponseDto
     * При вводе не положительногоы числа возвращает ошибку
     */
    @GetMapping("/days/{countDays}")
    public ResponseEntity<List<ForecastWeatherResponseDto>> getWeatherForSomeDaysInExactCity(@PathVariable Integer countDays) {
        if (countDays <= 0) {
            return ResponseEntity.badRequest().header("Ошибка! Введите положительное число дней").build();
        } else {
            WeatherRequest request = new WeatherRequest(Location.GOMEL_LONGITUDE.getCoord(), Location.GOMEL_LATITUDE.getCoord(), countDays);
            return ResponseEntity.ok(weatherService.getForecastWeather(request));
        }
    }

    /**
     * Метод обрабатывающий Rest-запросы с префиксом /api/weather/city.
     * Возвращает погоду по указанным координатам долготы и ширины
     *
     * @param request принимает параметры в виде json с координатами долготы и ширины
     * @return json, определенный моделью WeatherResponseDto
     */
    @GetMapping("/city")
    public ResponseEntity<WeatherResponseDto> getWeatherInSomeCity(@RequestBody WeatherRequest request) {
        return getWeather(request);
    }

    /**
     * Метод для работы по слоем Сервис
     * Возвращает погоду для города по координатам пришедшим в запросе
     *
     * @param request объект сформированный из запроса пользователя
     * @return json, определенный моделью WeatherResponseDto
     * В случае не корректных данных ловится исключение IncorrectDataOfWeather
     * и возвращается ResponseEntity c не найденным результатом
     */
    private ResponseEntity<WeatherResponseDto> getWeather(WeatherRequest request) {
        try {
            return ResponseEntity.ok(weatherService.getWeather(request));
        } catch (IncorrectDataOfWeather e) {
            return ResponseEntity.notFound().header(e.getMessage()).build();
        }
    }

}

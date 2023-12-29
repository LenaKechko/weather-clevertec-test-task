package ru.clevertec.weathertesttask.sevice.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.weathertesttask.dto.ForecastWeatherResponseDto;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
import ru.clevertec.weathertesttask.entity.YandexResponse;
import ru.clevertec.weathertesttask.entity.model.WeatherModel;
import ru.clevertec.weathertesttask.exception.IncorrectDataOfWeather;
import ru.clevertec.weathertesttask.mapper.ForecastModelMapper;
import ru.clevertec.weathertesttask.model.WeatherCondition;
import ru.clevertec.weathertesttask.model.WeatherRequest;
import ru.clevertec.weathertesttask.model.WeatherWindDir;
import ru.clevertec.weathertesttask.repository.impl.WeatherRepository;
import ru.clevertec.weathertesttask.sevice.WeatherService;

import java.util.Collection;
import java.util.List;

/**
 * Класс отвечающий за слой сервиса
 * Аннотация @Service указывает на то, что класс является бином
 * Аннотация @RequiredArgsConstructor заменяет написание конструктора со всеми полями класса
 */

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    /**
     * Поле для работы со слоем репозитория
     */
    private final WeatherRepository weatherRepository;

    /**
     * Метод для получения погоды в текущий момент по координатам города
     *
     * @param request объект содержащий долготу и широту города
     * @return объект WeatherResponseDto, содержащий данные о погоде
     * @throws IncorrectDataOfWeather вызывается случае неккоректно введенных данных
     */
    @Override
    public WeatherResponseDto getWeather(WeatherRequest request) {
        return weatherRepository.getWeather(request.longitude(), request.latitude(), request.limit())
                .map(response -> new WeatherResponseDto(
                        response.date().withZoneSameInstant(response.info().tzInfo().nameTimeZone()),
                        new WeatherModel(response.model().temperature(),
                                response.model().feelsTemperature(),
                                WeatherCondition.getCondition(response.model().condition()),
                                response.model().windSpeed(),
                                response.model().windGust(),
                                WeatherWindDir.getWindDir(response.model().windDir()),
                                response.model().pressureInMm()
                        )
                ))
                .orElseThrow(() -> new IncorrectDataOfWeather(request.longitude(), request.latitude()));
    }

    /**
     * Метод для получения погоды в на несколько дней по координатам города
     *
     * @param request объект содержащий долготу и широту города, количество дней
     * @return список объектов ForecastWeatherResponseDto
     */
    @Override
    public List<ForecastWeatherResponseDto> getForecastWeather(WeatherRequest request) {
        return weatherRepository.getWeather(request.longitude(), request.latitude(), request.limit())
                .stream()
                .map(YandexResponse::forecast)
                .flatMap(Collection::stream)
                .map(ForecastModelMapper::toForecastWeatherResponseDto)
                .toList();
    }

}

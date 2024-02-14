package ru.clevertec.weathertesttask.repository.impl;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.weathertesttask.constant.Location;
import ru.clevertec.weathertesttask.entity.IYandexResponse;
import ru.clevertec.weathertesttask.entity.YandexResponse;
import ru.clevertec.weathertesttask.repository.IWeatherRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс для работы со слоем репозитория
 * Выполняет запросы к внешнему Api
 * Аннотация @Component указывает на то, что класс является бином
 * Аннотация @RequiredArgsConstructor заменяет написание конструктора со всеми полями класса
 */
@Component
@RequiredArgsConstructor
public class WeatherRepository implements IWeatherRepository {

    /**
     * Поле для работы с ответом внешнего Api
     */
    private final IYandexResponse response;

    private static final Executor EXECUTOR = Executors.newFixedThreadPool(5);


    /**
     * Метод для получения данных от внешнего api Яндекс.Погода
     *
     * @param longitude координата долготы
     * @param latitude  координата широты
     * @param limit     количество дней, если необходимо
     * @return Optional<YandexResponse> для дальнейшей обработки ошибочных данных
     */
    @Timed("gettingWeather")
    public Optional<YandexResponse> getWeather(Double longitude, Double latitude, Integer limit) {
        CompletableFuture<Optional<YandexResponse>> yandexResponseFuture = CompletableFuture
                .supplyAsync(() -> Optional.ofNullable(response.getWeather(longitude, latitude, limit)), EXECUTOR)
                .exceptionally((ex) -> Optional.empty());
        return yandexResponseFuture.join();

//        try {
//            return Optional.ofNullable(response.getWeather(longitude, latitude, limit));
//        } catch (FeignException.FeignClientException e) {
//            return Optional.empty();
//        }
    }

    public List<YandexResponse> getWeathers(Double longitude, Double latitude, Integer limit) {
        CompletableFuture<YandexResponse> yandexResponseFuture = CompletableFuture
                .supplyAsync(() -> response.getWeather(longitude, latitude, limit), EXECUTOR);

        CompletableFuture<YandexResponse> yandexResponseForLondonFuture = CompletableFuture
                .supplyAsync(() -> response.getWeather(Location.LONDON_LONGITUDE.getCoord(),
                        Location.LONDON_LATITUDE.getCoord(), limit), EXECUTOR);

        return yandexResponseFuture.thenCombine(yandexResponseForLondonFuture,
                        (city, london) -> Stream.of(city, london)
                                .collect(Collectors.toList()))
                .join();
    }
}

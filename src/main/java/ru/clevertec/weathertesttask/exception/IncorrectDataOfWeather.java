package ru.clevertec.weathertesttask.exception;

/**
 * Класс определящий исключение о некорректности данных в Rest-запросе
 */
public class IncorrectDataOfWeather extends RuntimeException {

    /**
     * Конструктор с поясняющим сообщением
     *
     * @param longitude координата долготы
     * @param latitude  координата широты
     */
    public IncorrectDataOfWeather(Double longitude, Double latitude) {
        super("Incorrect longitude = " + longitude + " or latitude = " + latitude);
    }
}

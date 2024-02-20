package ru.clevertec.weathertesttask.constant;

import lombok.Getter;

/**
 * Перечисление для координат города Гомель
 */
@Getter
public enum Location {

    /**
     * Долгота и широта
     */
    GOMEL_LATITUDE(52.4345),
    GOMEL_LONGITUDE(30.9754),
    LONDON_LATITUDE(51.5085),
    LONDON_LONGITUDE(-0.12574);
    /**
     * Поле для работы со значениями координат
     */
    private final Double coord;

    /**
     * Кнструктор
     *
     * @param coord координата
     */

    Location(Double coord) {
        this.coord = coord;
    }
}

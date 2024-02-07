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
    GOMEL_LONGITUDE(52.4345),
    GOMEL_LATITUDE(30.9754);

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

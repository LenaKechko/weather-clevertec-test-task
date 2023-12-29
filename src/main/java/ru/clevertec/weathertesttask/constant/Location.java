package ru.clevertec.weathertesttask.constant;

import lombok.Getter;

@Getter
public enum Location {

    GOMEL_LONGITUDE(52.4345),
    GOMEL_LATITUDE(30.9754);

    private final Double coord;

    Location(Double coord) {
        this.coord = coord;
    }
}

package ru.clevertec.weathertesttask.data;

import ru.clevertec.weathertesttask.constant.Location;
import ru.clevertec.weathertesttask.model.WeatherCondition;
import ru.clevertec.weathertesttask.model.WeatherWindDir;

import java.time.*;

public class Constants {

    public static final LocalDate DATE = LocalDate.MAX;
    public static final Double TEMPERATURE = 4.0;
    public static final Double MIN_TEMPERATURE = -1.0;
    public static final Double AVG_TEMPERATURE = 0.0;
    public static final Double MAX_TEMPERATURE = 1.0;
    public static final Double FEELS_TEMPERATURE = 0.0;
    public static final Double WIND_SPEED = 5.1;
    public static final Double WIND_GUST = 3.0;
    public static final String WIND_DIR = "ne";
    public static final String WIND_DIR_FROM_ENUM = WeatherWindDir.NE.getNameWindDir();
    public static final Integer PRESSURE_IN_MM = 733;
    public static final String CONDITION = "cloudy";
    public static final String CONDITION_FROM_ENUM = WeatherCondition.CLOUDY.getNameCondition();
    public static final ZoneId NAME_TIME_ZONE = ZoneId.systemDefault();
    public static final ZoneOffset COUNT_TIME_ZONE = NAME_TIME_ZONE.getRules().getOffset(Instant.now());
    public static final Double LONGITUDE = Location.GOMEL_LONGITUDE.getCoord();
    public static final Double LATITUDE = Location.GOMEL_LATITUDE.getCoord();
    public static final Integer LIMIT = 1;
    public static final ZonedDateTime DATE_WITH_ZONED = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());
}

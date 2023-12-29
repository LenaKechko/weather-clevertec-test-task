package ru.clevertec.weathertesttask.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.clevertec.weathertesttask.entity.model.WeatherModel;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Класс, определяющий вид сущности записываемой в БД
 * В аннотации @Document указывается наименование коллекции в БД
 * Для конструкторов, сеттеров и геттеров использовались аннотации lombok
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "weather")
public class WeatherResponse {

    /**
     * Идентификатор. Задается при маппинге
     */
    @Id
    private UUID id;
    /**
     * Время запрашиваемого прогноза погоды
     */
    private ZonedDateTime date;
    /**
     * Модель прогноза погоды
     */
    private WeatherModel model;

}


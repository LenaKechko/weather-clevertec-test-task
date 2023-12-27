package ru.clevertec.weathertesttask.db.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
import ru.clevertec.weathertesttask.entity.WeatherResponse;

import java.util.UUID;

@Repository
public interface IWeatherDBRepository extends MongoRepository<WeatherResponse, UUID> {
}

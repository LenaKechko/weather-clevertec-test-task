package ru.clevertec.weathertesttask.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import ru.clevertec.weathertesttask.db.repository.IWeatherDBRepository;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
import ru.clevertec.weathertesttask.entity.WeatherResponse;
import ru.clevertec.weathertesttask.mapper.WeatherResponseMapper;
import ru.clevertec.weathertesttask.model.WeatherRequest;

import java.time.Duration;

@Component
public class ProgrammaticallyScheduledTask {

    private final TaskScheduler taskScheduler;
    private final WeatherService service;
    private final WeatherResponseMapper mapper;
    private final IWeatherDBRepository repository;

    @Autowired
    public ProgrammaticallyScheduledTask(TaskScheduler taskScheduler,
                                         WeatherService service,
                                         WeatherResponseMapper mapper,
                                         IWeatherDBRepository repository) {
        this.taskScheduler = taskScheduler;
        this.service = service;
        this.mapper = mapper;
        this.repository = repository;
        scheduleTask();
    }


    public void scheduleTask() {
        Runnable task = () -> {
            WeatherResponseDto responseDto = service.getWeather(new WeatherRequest(null, null, null));
            WeatherResponse response = mapper.toWeatherResponse(responseDto);
            repository.insert(response);
        };
        taskScheduler.scheduleWithFixedDelay(task, Duration.ofMinutes(10));
    }
}

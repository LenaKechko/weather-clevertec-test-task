package ru.clevertec.weathertesttask.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import ru.clevertec.weathertesttask.repository.db.IWeatherDBRepository;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
import ru.clevertec.weathertesttask.entity.WeatherResponse;
import ru.clevertec.weathertesttask.mapper.WeatherResponseMapper;
import ru.clevertec.weathertesttask.model.WeatherRequest;

import java.time.Duration;

/**
 * Класс реализующий задачу: делать запрос о погоде каждые 10 минут
 * Аннотация @Component указывает на то, что класс является бином
 */
@Component
public class ProgrammaticallyScheduledTask {

    /**
     * Поле типа интерфейса TaskScheduler,
     * который помогает реализовывать задачи повторяющием по расписанию
     */
    private final TaskScheduler taskScheduler;
    /**
     * Поле слоя сервис
     */
    private final WeatherService service;
    /**
     * Поле слоя маппер
     * Позволяется преобазовывать объекты к необходимому виду
     */
    private final WeatherResponseMapper mapper;
    /**
     * Репозиторий, работающий с базой данных
     */
    private final IWeatherDBRepository repository;

    /**
     * Конструктор класса. Запускает задачу на выполнение
     *
     * @param taskScheduler объект для программирования задач по расписанию
     * @param service       объект слоя сервис
     * @param mapper        объект слоя маппер
     * @param repository    объект слоя репозиторий
     */
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

    /**
     * Метод выполняет запрос к внешнему api,
     * преобразует объект и записывает его в БД MongoDB.
     * Действие запускается каждые 10 минут
     */
    public void scheduleTask() {
        Runnable task = () -> {
            WeatherResponseDto responseDto = service.getWeather(new WeatherRequest(null, null, null));
            WeatherResponse response = mapper.toWeatherResponse(responseDto);
            repository.insert(response);
        };
        taskScheduler.scheduleWithFixedDelay(task, Duration.ofMinutes(10));
    }
}

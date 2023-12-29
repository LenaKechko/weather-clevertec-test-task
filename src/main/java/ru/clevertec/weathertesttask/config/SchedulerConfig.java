package ru.clevertec.weathertesttask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Класс для настройки работы задачи по расписанию
 */
@Configuration
public class SchedulerConfig {

    /**
     * Бин для предоставления пула-потоков
     *
     * @return пул
     */
    @Bean
    public TaskScheduler taskScheduler() {
        return new ThreadPoolTaskScheduler();
    }
}


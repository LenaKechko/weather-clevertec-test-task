//package ru.clevertec.weathertesttask.sevice;
//
//import io.micrometer.core.instrument.MeterRegistry;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mockito;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.scheduling.TaskScheduler;
//import ru.clevertec.weathertesttask.data.WeatherRequestTestData;
//import ru.clevertec.weathertesttask.data.WeatherResponseTestData;
//import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
//import ru.clevertec.weathertesttask.mapper.WeatherResponseMapper;
//import ru.clevertec.weathertesttask.model.WeatherRequest;
//import ru.clevertec.weathertesttask.repository.db.IWeatherDBRepository;
//
//import java.util.concurrent.atomic.AtomicInteger;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static reactor.core.publisher.Mono.when;
//
//@DataMongoTest
//class ProgrammaticallyScheduledTaskTest {
//
//    @MockBean
//    private TaskScheduler taskScheduler;
//    @MockBean
//    private WeatherService service;
//    @MockBean
//    private WeatherResponseMapper mapper;
//    @MockBean
//    private IWeatherDBRepository repository;
//
//    private MeterRegistry meterRegistry;
//    private final AtomicInteger temperature = new AtomicInteger();
//    @InjectMocks
//    private ProgrammaticallyScheduledTask programmaticallyScheduledTask;
//
//    @BeforeEach
//    void setUp() {
//        meterRegistry.gauge("temperatureScheduler", temperature);
//    }
//
//    @Test
//    void scheduleTask() {
//        WeatherRequest weatherRequest = WeatherRequestTestData.builder().build().buildWeatherRequest();
//        WeatherResponseDto responseDto = WeatherResponseTestData.builder().build().buildWeatherDto();
//
//        Mockito.when(service.getWeather(weatherRequest))
//                .thenReturn(responseDto);
//
//    }
//}
package ru.clevertec.weathertesttask.data;

import lombok.Builder;
import lombok.Getter;
import ru.clevertec.weathertesttask.dto.WeatherResponseDto;
import ru.clevertec.weathertesttask.entity.WeatherResponse;
import ru.clevertec.weathertesttask.entity.model.WeatherModel;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Builder(setterPrefix = "with", toBuilder = true)
public class WeatherResponseTestData {

    @Builder.Default
    private UUID id = UUID.randomUUID();

    @Getter
    @Builder.Default
    private ZonedDateTime date = Constants.DATE_WITH_ZONED;

    @Builder.Default
    private WeatherModel model = WeatherModelTestData.builder()
            .withCondition(Constants.CONDITION_FROM_ENUM)
            .withWindDir(Constants.WIND_DIR_FROM_ENUM)
            .build().buildWeatherModel();


    public WeatherResponse buildWeather() {
        return new WeatherResponse(id, date, model);
    }

    public WeatherResponseDto buildWeatherDto() {
        return new WeatherResponseDto(date, model);
    }

    public List<WeatherResponseDto> buildListWeatherDto(int count) {
        return IntStream.range(0, count)
                .mapToObj((value) -> buildWeatherDto())
                .toList();
    }

}

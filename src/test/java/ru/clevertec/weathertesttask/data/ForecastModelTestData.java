package ru.clevertec.weathertesttask.data;

import lombok.Builder;
import ru.clevertec.weathertesttask.dto.ForecastWeatherResponseDto;
import ru.clevertec.weathertesttask.entity.model.ForecastModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Builder(setterPrefix = "with", toBuilder = true)
public class ForecastModelTestData {

    @Builder.Default
    private LocalDate date = Constants.DATE;

    @Builder.Default
    private ForecastModel.PartOfDay partOfDayList = new ForecastModel.PartOfDay(
            DayWeatherModelTestData.builder().build().buildDayWeatherModel(),
            DayWeatherModelTestData.builder().build().buildDayWeatherModel(),
            DayWeatherModelTestData.builder().build().buildDayWeatherModel(),
            DayWeatherModelTestData.builder().build().buildDayWeatherModel());

    public ForecastModel buildForecastModel() {
        return new ForecastModel(date, partOfDayList);
    }

    public ForecastWeatherResponseDto buildForecastWeatherResponseDto() {
        return new ForecastWeatherResponseDto(date,
                Map.of("day", partOfDayList.day(),
                        "morning", partOfDayList.morning(),
                        "evening", partOfDayList.evening(),
                        "night", partOfDayList.night()));
    }

    public List<ForecastWeatherResponseDto> buildListForecastWeatherResponseDto(int count) {
        if (count > 7) {
            count = 7;
        }
        return IntStream.range(0, count)
                .mapToObj((value) -> buildForecastWeatherResponseDto())
                .toList();
    }

}
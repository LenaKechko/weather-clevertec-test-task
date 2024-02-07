package ru.clevertec.weathertesttask.data;

import lombok.Builder;
import ru.clevertec.weathertesttask.dto.ForecastWeatherResponseDto;
import ru.clevertec.weathertesttask.entity.model.ForecastModel;

import java.time.LocalDate;
import java.util.Map;

@Builder(setterPrefix = "with", toBuilder = true)
public class ForecastModelTestData {

    @Builder.Default
    private LocalDate date = LocalDate.MAX;

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
                Map.of( "day", partOfDayList.day(),
                        "morning", partOfDayList.morning(),
                        "evening", partOfDayList.evening(),
                        "night", partOfDayList.night()));
    }

}
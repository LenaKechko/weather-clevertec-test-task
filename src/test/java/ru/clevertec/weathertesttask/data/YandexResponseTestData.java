package ru.clevertec.weathertesttask.data;

import lombok.Builder;
import ru.clevertec.weathertesttask.entity.YandexResponse;
import ru.clevertec.weathertesttask.entity.model.ForecastModel;
import ru.clevertec.weathertesttask.entity.model.InfoModel;
import ru.clevertec.weathertesttask.entity.model.WeatherModel;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Builder(setterPrefix = "with", toBuilder = true)
public class YandexResponseTestData {

    @Builder.Default
    private ZonedDateTime date = Constants.DATE_WITH_ZONED;

    @Builder.Default
    private WeatherModel model = WeatherModelTestData.builder().build().buildWeatherModel();

    @Builder.Default
    private InfoModel info = InfoModelTestData.builder().build().buildInfoModel();

    @Builder.Default
    private List<ForecastModel> forecast = List.of(ForecastModelTestData.builder().build().buildForecastModel(),
            ForecastModelTestData.builder()
                    .withDate(LocalDate.MIN)
                    .build().buildForecastModel());

    public YandexResponse buildYandexResponse() {
        return new YandexResponse(date, model, info, forecast);
    }

}

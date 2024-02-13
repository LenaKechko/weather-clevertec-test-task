package ru.clevertec.weathertesttask.data;

import lombok.Builder;
import ru.clevertec.weathertesttask.entity.model.TZInfoModel;

import java.time.ZoneId;
import java.time.ZoneOffset;

@Builder(setterPrefix = "with", toBuilder = true)
public class TZInfoModelTestData {

    @Builder.Default
    private ZoneId nameTimeZone = Constants.NAME_TIME_ZONE;
    @Builder.Default
    private ZoneOffset countTimeZone = Constants.COUNT_TIME_ZONE;

    public TZInfoModel buildTZInfoModel() {
        return new TZInfoModel(nameTimeZone, countTimeZone);
    }
}

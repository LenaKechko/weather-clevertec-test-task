package ru.clevertec.weathertesttask.data;

import lombok.Builder;
import ru.clevertec.weathertesttask.entity.model.TZInfoModel;

import java.time.ZoneId;

@Builder(setterPrefix = "with", toBuilder = true)
public class TZInfoModelTestData {

    @Builder.Default
    private ZoneId nameTimeZone = Constants.NAME_TIME_ZONE;

    public TZInfoModel buildTZInfoModel() {
        return new TZInfoModel(nameTimeZone);
    }
}

package ru.clevertec.weathertesttask.data;

import lombok.Builder;
import ru.clevertec.weathertesttask.entity.model.TZInfoModel;

import java.time.ZoneId;
import java.time.ZoneOffset;

@Builder(setterPrefix = "with", toBuilder = true)
public class TZInfoModelTestData {

    @Builder.Default
    private ZoneId nameTimeZone = ZoneId.of("Asia/Tehran");
    @Builder.Default
    private ZoneOffset countTimeZone = ZoneOffset.of("+0330");

    public TZInfoModel buildTZInfoModel() {
        return new TZInfoModel(nameTimeZone, countTimeZone);
    }
}

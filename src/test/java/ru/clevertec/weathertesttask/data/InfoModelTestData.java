package ru.clevertec.weathertesttask.data;

import lombok.Builder;
import lombok.Getter;
import ru.clevertec.weathertesttask.entity.model.InfoModel;
import ru.clevertec.weathertesttask.entity.model.TZInfoModel;

@Getter
@Builder(setterPrefix = "with", toBuilder = true)
public class InfoModelTestData {

    @Builder.Default
    TZInfoModel tzInfo = TZInfoModelTestData.builder().build().buildTZInfoModel();

    public InfoModel buildInfoModel() {
        return new InfoModel(tzInfo);
    }
}

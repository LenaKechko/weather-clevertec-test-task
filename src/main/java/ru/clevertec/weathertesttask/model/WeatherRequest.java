package ru.clevertec.weathertesttask.model;

/**
 * Запись для определения объекта. содержащего запрос от пользователя
 *
 * @param longitude координата долготы
 * @param latitude  координата широты
 * @param limit     количество дней
 */

public record WeatherRequest(Double longitude, Double latitude, Integer limit) {

    /**
     * Конструктор. Если в запросе не определены широта и долгота,
     * то по умолчанию определяются координаты города Гомель.
     * Если не передается количество дней limit, то определяется, что запрос делался на один день.
     * Если количество дней было введено больше допустимоо значения (7 дней),
     * то по умолчанию в запрос передается 7 дней.
     *
     * @param longitude координата долготы
     * @param latitude  координата широты
     * @param limit     количество дней
     */
    public WeatherRequest {
        if (longitude == null) {
            longitude = 52.4345;
        }
        if (latitude == null) {
            latitude = 30.9754;
        }
        if (limit == null) {
            limit = 1;
        }
        if (limit > 7) {
            limit = 7;
        }
    }
}

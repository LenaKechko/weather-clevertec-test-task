package ru.clevertec.weathertesttask.entity;

//@SpringBootTest
//@ActiveProfiles("test")
//@EnableConfigurationProperties
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {WireMockConfig.class})
class IYandexResponseTest {

//    @Autowired
//    private WireMockServer mockWeatherService;
//
//    @Autowired
//    private IYandexResponse yandexResponse;
//
//    @BeforeEach
//    void setUp() throws IOException {
//        WeatherMock.setupMockWeatherResponse(mockWeatherService);
//    }
//
//    @Test
//    public void getWeatherShouldReturnJsonWithWeather() {
//        // given
//        DayWeatherModel day = DayWeatherModelTestData.builder()
//                .withMinTemperature(5.0)
//                .withAvgTemperature(8.0)
//                .withMaxTemperature(9.0)
//                .withWindSpeed(3.8)
//                .withWindGust(7.1)
//                .withWindDir("ne")
//                .withPressureInMm(568)
//                .withCondition("cloudy")
//                .build().buildDayWeatherModel();
//        DayWeatherModel morning = DayWeatherModelTestData.builder()
//                .withMinTemperature(1.0)
//                .withAvgTemperature(4.0)
//                .withMaxTemperature(7.0)
//                .withWindSpeed(2.9)
//                .withWindGust(5.6)
//                .withWindDir("nw")
//                .withPressureInMm(569)
//                .withCondition("cloudy")
//                .build().buildDayWeatherModel();
//        DayWeatherModel night = DayWeatherModelTestData.builder()
//                .withMinTemperature(2.0)
//                .withAvgTemperature(3.0)
//                .withMaxTemperature(4.0)
//                .withWindSpeed(3.4)
//                .withWindGust(4.5)
//                .withWindDir("sw")
//                .withPressureInMm(567)
//                .withCondition("overcast")
//                .build().buildDayWeatherModel();
//        DayWeatherModel evening = DayWeatherModelTestData.builder()
//                .withMinTemperature(4.0)
//                .withAvgTemperature(4.0)
//                .withMaxTemperature(5.0)
//                .withWindSpeed(2.8)
//                .withWindGust(4.3)
//                .withWindDir("ne")
//                .withPressureInMm(567)
//                .withCondition("cloudy")
//                .build().buildDayWeatherModel();
//
//        ForecastModel forecastFirstDay = ForecastModelTestData.builder()
//                .withDate(LocalDate.parse("2023-12-29"))
//                .withPartOfDayList(
//                        new ForecastModel.PartOfDay(day, morning, night, evening)
//                )
//                .build().buildForecastModel();
//
//        day = DayWeatherModelTestData.builder()
//                .withMinTemperature(7.0)
//                .withAvgTemperature(9.0)
//                .withMaxTemperature(10.0)
//                .withWindSpeed(4.3)
//                .withWindGust(8.4)
//                .withWindDir("sw")
//                .withPressureInMm(575)
//                .withCondition("cloudy")
//                .build().buildDayWeatherModel();
//        morning = DayWeatherModelTestData.builder()
//                .withMinTemperature(0.0)
//                .withAvgTemperature(4.0)
//                .withMaxTemperature(9.0)
//                .withWindSpeed(2.8)
//                .withWindGust(5.4)
//                .withWindDir("e")
//                .withPressureInMm(568)
//                .withCondition("clear")
//                .build().buildDayWeatherModel();
//        night = DayWeatherModelTestData.builder()
//                .withMinTemperature(0.0)
//                .withAvgTemperature(1.0)
//                .withMaxTemperature(3.0)
//                .withWindSpeed(2.3)
//                .withWindGust(3.4)
//                .withWindDir("e")
//                .withPressureInMm(568)
//                .withCondition("clear")
//                .build().buildDayWeatherModel();
//        evening = DayWeatherModelTestData.builder()
//                .withMinTemperature(4.0)
//                .withAvgTemperature(5.0)
//                .withMaxTemperature(6.0)
//                .withWindSpeed(2.5)
//                .withWindGust(3.4)
//                .withWindDir("e")
//                .withPressureInMm(576)
//                .withCondition("cloudy")
//                .build().buildDayWeatherModel();
//
//        ForecastModel forecastSecondDay = ForecastModelTestData.builder()
//                .withDate(LocalDate.parse("2023-12-30"))
//                .withPartOfDayList(
//                        new ForecastModel.PartOfDay(day, morning, night, evening)
//                )
//                .build().buildForecastModel();
//
//        YandexResponse expected = new YandexResponse(ZonedDateTime.parse("2023-12-29T15:27:42.715478Z"),
//                WeatherModelTestData.builder().build().buildWeatherModel(),
//                InfoModelTestData.builder().build().buildInfoModel(),
//                List.of(forecastFirstDay, forecastSecondDay));
//
//        // when
//        YandexResponse actual = yandexResponse.getWeather(Location.GOMEL_LONGITUDE.getCoord(),
//                Location.GOMEL_LATITUDE.getCoord(), 1);
//
//        // then
//        assertThat(actual.date())
//                .isEqualTo(expected.date());
//    }
}

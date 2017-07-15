package pl.kubal.models.service;

/**
 * Created by Jim on 2017-07-15.
 */
public interface IWeatherObserver {
    void onWeatherUpdate(WeatherInfo weatherInfo);
}

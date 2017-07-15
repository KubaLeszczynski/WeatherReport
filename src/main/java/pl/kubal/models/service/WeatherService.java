package pl.kubal.models.service;

import org.json.JSONObject;
import pl.kubal.models.Config;
import pl.kubal.models.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Jim on 2017-07-15.
 */
public class WeatherService {
    private static WeatherService ourInstance = new WeatherService();

    public static WeatherService getInstance() {
        return ourInstance;
    }


    //Dane  API
    private String appurl;

    //Dane z API
    private double temp;
    private int humidity;
    private int pressure;
    private int cloudy;
    private String cityName;

    //Observer pattern
    List<IWeatherObserver> observerList = new ArrayList<>();

    private ExecutorService executorService;

    private WeatherService(){
        executorService = Executors.newFixedThreadPool(2);
        appurl ="";
    }
    public void makeCall(String city, String country){
        executorService.execute(() ->{
        String link = Config.APPURL + "weather" + "?q=" + city + "," + country + "&appid=" + Config.APPID;
            parseJsonData(Utils.connectAndResponse(link),city);
        });

    }

    private synchronized void parseJsonData(String data, String city) {
        JSONObject rootObject = new JSONObject(data);
        JSONObject mainObject = rootObject.getJSONObject("main");

        temp = mainObject.getDouble("temp");
        humidity = mainObject.getInt("humidity");
        pressure = mainObject.getInt("pressure");

        //Observer pattern


        JSONObject cloudsObject = rootObject.getJSONObject("clouds");
        cloudy = cloudsObject.getInt("all");
        cityName = city;

        informObservers();
    }
        public void addWeatherObserver(IWeatherObserver observer){
            observerList.add(observer);
    }
        public void removeWeatherObserver(IWeatherObserver observer){
            observerList.remove(observer);
        }
        private void informObservers(){
            WeatherInfo weatherInfo = new WeatherInfo(temp,humidity,pressure,cloudy,cityName);
            observerList.forEach(s -> {
                s.onWeatherUpdate(weatherInfo);
            });
        }

    }


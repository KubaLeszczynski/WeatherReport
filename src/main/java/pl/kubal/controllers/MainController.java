package pl.kubal.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import pl.kubal.models.service.IWeatherObserver;
import pl.kubal.models.service.WeatherInfo;
import pl.kubal.models.service.WeatherService;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable, IWeatherObserver {

    @FXML
    private Button weatherCall;

    private WeatherService weatherService = WeatherService.getInstance();

    public void initialize(URL location, ResourceBundle resources){
        weatherService.addWeatherObserver(this);

        weatherCall.setOnMouseClicked(e-> {
            weatherService.makeCall("Wroclaw", "pl");
            weatherService.makeCall("Warsaw", "pl");
//            System.out.println("Temperatura: " + weatherService.getTemperature());
//            System.out.println("Wilgotnosc: " + weatherService.getHumidity());
//            System.out.println("Cisnienie: " + weatherService.getPressure());
//            System.out.println("Zachmurzenie: " + weatherService.getCloudy());
        });
    }

    @Override
    public void onWeatherUpdate(WeatherInfo weatherInfo) {
        System.out.println("Temperatura z onWeatherUpdate: "+ weatherInfo.getCityName()+" "
                + weatherInfo.getTemperatureCelsius());
    }
}

package pl.kubal.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import pl.kubal.models.service.WeatherService;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    Button weatherCall;

    private WeatherService weatherService = WeatherService.getInstance();

    public void initialize(URL location, ResourceBundle resources){

        weatherCall.setOnMouseClicked(e-> {
            weatherService.makeCall("Wroclaw", "pl");
            System.out.println("Temperatura: " + weatherService.getTemperature());
        });
    }
}

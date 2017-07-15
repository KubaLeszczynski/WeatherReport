package pl.kubal.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pl.kubal.models.service.IWeatherObserver;
import pl.kubal.models.service.WeatherInfo;
import pl.kubal.models.service.WeatherService;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable, IWeatherObserver {

    @FXML
    private Button weatherCall;

    @FXML
    private Label textWeather;

    @FXML
    private TextField weatherCity;

    @FXML
    ProgressIndicator progressBar;

    private WeatherService weatherService = WeatherService.getInstance();

    public void initialize(URL location, ResourceBundle resources){
        weatherService.addWeatherObserver(this);

        progressBar.setVisible(false);

        weatherCall.setOnMouseClicked(e-> {
            if(!weatherCity.getText().isEmpty() && weatherCity.getText().length()>3) {
                weatherService.makeCall(weatherCity.getText(), "pl");
            }else{
                //TODO add this to Utils class
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Błąd");
                alert.setContentText("NIeprawidłowa nazwa miast");
                alert.show();
            }
            //weatherService.makeCall("Warsaw", "pl");
//            System.out.println("Temperatura: " + weatherService.getTemperature());
//            System.out.println("Wilgotnosc: " + weatherService.getHumidity());
//            System.out.println("Cisnienie: " + weatherService.getPressure());
//            System.out.println("Zachmurzenie: " + weatherService.getCloudy());
        });
    }

    @Override
    public void onWeatherUpdate(WeatherInfo weatherInfo) {
//        System.out.println("Temperatura z onWeatherUpdate: "+ weatherInfo.getCityName()+" "
//                + weatherInfo.getTemperatureCelsius());
        textWeather.setText("Temperatura: "+ weatherInfo.getTemperatureCelsius()+ "\r\n"
        + "Wilgotnosc: " + weatherInfo.getHumidity() + "\r\n"
        + "Ciśnienie: " + weatherInfo.getPressure() + "\r\n"
        + "Zacgmurzenie: " + weatherInfo.getCloudy()
        );
        progressBar.setVisible(false);
    }

}

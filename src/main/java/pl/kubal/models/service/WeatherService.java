package pl.kubal.models.service;

import org.json.JSONObject;
import pl.kubal.models.Config;
import pl.kubal.models.Utils;

/**
 * Created by Jim on 2017-07-15.
 */
public class WeatherService {
    private static WeatherService ourInstance = new WeatherService();

    public static WeatherService getInstance() {
        return ourInstance;
    }


    //Dane z API
    private String appurl;

    private double temp;

    private WeatherService(){

    }
    public void makeCall(String city, String country){
        appurl = Config.APPURL + "weather" + "?q=" + city + "," + country + "&appid=" + Config.APPID;
        System.out.println(Utils.connectAndResponse(appurl));
        parseJsonData(Utils.connectAndResponse(appurl));
    }

    private void parseJsonData(String data){
        JSONObject rootObject = new JSONObject(data);
        JSONObject mainObject = rootObject.getJSONObject("main");

        temp= mainObject.getDouble("temp");
    }
    //dostahemy sie do danych
    public  double getTemperature(){
        return temp;
    }
}

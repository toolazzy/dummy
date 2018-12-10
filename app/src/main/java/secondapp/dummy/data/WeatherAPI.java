package secondapp.dummy.data;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ravi upreti on 10/14/2018.
 */

public class WeatherAPI {
    private static final String baseUrl = "http://dataservice.accuweather.com/";
    public static WeatherService weatherService = null;

    //to implement singleton pattern
    public static WeatherService getRedditService() {

        if (weatherService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            weatherService = retrofit.create(WeatherService.class);
        }
        return weatherService;
    }

    public interface WeatherService {
            @GET("forecasts/v1/daily/5day/202396")
            Call<WeatherResponse> getWeather(@Query("apikey") String apiKey);

    }
}

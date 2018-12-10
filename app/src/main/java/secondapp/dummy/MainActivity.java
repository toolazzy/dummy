package secondapp.dummy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import secondapp.dummy.data.ApiKey;
import secondapp.dummy.data.DailyForecast;
import secondapp.dummy.data.Day;
import secondapp.dummy.data.Maximum;
import secondapp.dummy.data.Minimum;
import secondapp.dummy.data.Temperature;
import secondapp.dummy.data.WeatherAPI;
import secondapp.dummy.data.WeatherResponse;

public class MainActivity extends AppCompatActivity {

    private RecyclerView textview1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiKey api=new ApiKey();
        textview1= (RecyclerView) findViewById(R.id.rlist);
        final Call<WeatherResponse> jsonData = WeatherAPI.getRedditService().getWeather(api.getApikey());
        jsonData.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                StringBuilder strBuilder=new StringBuilder("");
                List<DailyForecast> dailyForecastList=  response.body().getDailyForecasts();
                for (DailyForecast df:dailyForecastList) {
                    Day day=df.getDay();
                    String date=df.getDate();
                    Temperature temp=df.getTemperature();
                    Maximum maximum=temp.getMaximum();
                    Minimum min=temp.getMinimum();
                    int minTemp,maxTemp;
                    minTemp= Integer.valueOf(min.getValue());
                    maxTemp= Integer.valueOf(maximum.getValue());
                    String morning=day.getIconPhrase();
                    String night=df.getNight().getIconPhrase();
                    strBuilder.append(date+"\n"+minTemp+" "+maxTemp+" \n"+morning+"\n\n");

                }
                textview1.setAdapter(new dummyadapter(strBuilder));

            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to get response", Toast.LENGTH_SHORT).show();
            }
        });
}
}
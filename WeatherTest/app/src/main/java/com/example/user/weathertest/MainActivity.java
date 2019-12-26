package com.example.user.weathertest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private EditText cityName;
    private Button btnSearch;
    private TextView result;
    String resultText;
    Weather weather;

    class Weather extends AsyncTask<String, Void, String>
    { //First String means URL is in String , Void mean nothig , Third String means Return type will be String

        public void search(){

            String cName = cityName.getText().toString();


            String content;
            Weather weather = new Weather();

            try {

                content = weather.execute("https://openweathermap.org/data/2.5/weather?q="+cName+"&appid=b6907d289e10d714a6e88b30761fae22").get();
                //First we will check data is retrieve successfully or not
                Log.i("contentdata",content);

                //JSON
                JSONObject jsonObject = new JSONObject(content);
                String weatherData = jsonObject.getString("weather");
                String mainTemperature = jsonObject.getString("main"); //this main is not part of weather array, it`s seperate variable like wearher

//                String lon = jsonObject.getString("lon");
//                String lat = jsonObject.getString("lat");
                double visibility;

                Log.i("weatherData",weatherData);
                // weather data is in array
                JSONArray array = new JSONArray(weatherData);
//                JSONObject coord = jsonObject.getJSONObject("coord");

//                Log.d("위도경도", "search1"+lon);
//                Log.d("위도경도", "search"+lat);

                String main="";
                String description="";
                String temperature="";


                for(int i=0;i<array.length();i++){
                    JSONObject weatherPart = array.getJSONObject(i);
                    description = weatherPart.getString("description");
                    main = weatherPart.getString("main");

                }

                JSONObject mainPart = new JSONObject(mainTemperature);
                temperature = mainPart.getString("temp");
                visibility = Double.parseDouble(jsonObject.getString("visibility"));
                //By default visibility is meter
                int visibilityInKilometer;
                visibilityInKilometer = (int) visibility/1000;

                Log.i("Temperature",temperature);

                Log.i("main",main);
                Log.i("description",description);

                resultText="Main : "+ main+"\nDescription : "+description + "\nTemperature : "+temperature + "\nVisibility :"+visibilityInKilometer+" KM";


                result.setText(resultText);



                // Now we will sgiw this result on screen
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),resultText,Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        protected String doInBackground(String... address) {

            //String... means multiple address can be send. it acts as array

            try {
                URL url = new URL(address[0]);
                try {
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //Eastablish connection with address
                    connection.connect();

                    //retrieve data from url
                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    //Retrieve data and return it as String
                    int data = isr.read();
                    String content = "";
                    char ch;
                    while(data!=-1){
                        ch = (char)data;
                        content =  content + ch;
                        data = isr.read();
                    }
                    return content;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityName = findViewById(R.id.cityName);
        btnSearch = findViewById(R.id.btnSearch);
        result = findViewById(R.id.result);


        weather = new Weather();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weather.search();

                Toast.makeText(getApplicationContext(),resultText,Toast.LENGTH_SHORT).show();
                result.setText(resultText);
            }
        });

    }
}

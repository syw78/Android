package com.example.user.weatherjs;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String API = "4754a10491a092bfc66101b591c725c8";
    TextView tvResult; //날씨
    TextView tvMaxTemp;
    TextView tvWeather;
    ImageView imageView;
    String icon;
    static double LAT; //위도
    static double LON; //경도

    private GpsTracker gpsTracker;

    private boolean isdisconnected = false;

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    public MainActivity() {
    }


    // double lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.tvResult);
        tvMaxTemp = findViewById(R.id.tvMaxTemp);
        tvWeather = findViewById(R.id.tvWeather);
        imageView = findViewById(R.id.imageView);
        
        if (!checkLocationServicesStatus()) {

            showDialogForLocationServiceSetting();
        } else {

            checkRunTimePermission();
        }

        final TextView textview_address = (TextView) findViewById(R.id.textView); //주소
        Button button = (Button) findViewById(R.id.button); //버튼

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new weatherTask().execute();
                Toast.makeText(getApplicationContext(),"새로고침 완료",Toast.LENGTH_SHORT).show();
//                if (isdisconnected)
//                    Toast.makeText(getApplicationContext(), "인터넷을연결해주세요", Toast.LENGTH_SHORT).show();
//                //recreate();

            }
        });

        gpsTracker = new GpsTracker(getApplicationContext());

        //double latitude = gpsTracker.getLatitude();
        //double longitude = gpsTracker.getLongitude();

        LAT = gpsTracker.getLatitude();
        LON = gpsTracker.getLongitude();

        String address = getCurrentAddress(LAT, LON);
        textview_address.setText(address); //주소 세팅

        Toast.makeText(MainActivity.this, "현재위치 \n위도 " + LAT + "\n경도 " + LON, Toast.LENGTH_LONG).show();

        if (LAT == 0 && LON == 0) {
            Toast.makeText(getApplicationContext(), "가져올수없음", Toast.LENGTH_SHORT).show();
        } else {
            new weatherTask().execute();
        }


    }


    public class weatherTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection urlConnection = null; //HttpUrlConnection
            BufferedReader reader = null; //try가 아닌 곳에서도 사용 되므로 try 밖에 선언합니다.
            String forecastJsonStr = null; //불러온 데이터 저장에 사용할 변수 - try가 아닌 곳에서도 사용 되므로 try 밖에 선언합니다.
            try {
                //새 URL 객체
                final String WeatherURL = "https://api.openweathermap.org/data/2.5/weather?lat=" + LAT + "&lon=" + LON + "&units=metric&appid=" + API;
                URL url = new URL(WeatherURL);
                //새 URLConnection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                //InputStream 을 사용해 데이터 읽어들이기
                InputStream inputStream = urlConnection.getInputStream();


                //StringBuffer 에 데이터 저장
                StringBuffer buffer = new StringBuffer(); // 새로운 StringBuffer 생성
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                Log.d("테스트", "onClick12" + LAT + LON);
                if (buffer.length() == 0) {
                    // 불러온 데이터가 비어있음.
                    forecastJsonStr = null;
                }
                forecastJsonStr = buffer.toString(); //로드한 데이터 문자열 변수에 저장.

                Log.d("테스트", "doInBack" + forecastJsonStr);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                forecastJsonStr = null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect(); //HttpURLConnection 연결 끊기
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                    }
                }
            }


            return forecastJsonStr;

        }


        @Override      //결과값을 보여주는 함수
        protected void onPostExecute(String s) {

            if (s == null) {
                isdisconnected = true; //플래그줌
                if (isdisconnected)
                    Toast.makeText(getApplicationContext(), "최신화를 위해 인터넷을 연결 해주세요", Toast.LENGTH_SHORT).show();

                return; //리턴
            }else{
                isdisconnected = false;
            }


            JSONObject jsonObject;

            try {

                jsonObject = new JSONObject(s);

                if (jsonObject != null) {

                    JSONObject main = jsonObject.getJSONObject("main");
                    // sys에 국가와 일출시간, 일몰시간
                    JSONObject sys = jsonObject.getJSONObject("sys");
                    // wind에 풍속
                    JSONObject wind = jsonObject.getJSONObject("wind");
                    // weather에 날씨정보(ex.구름 많음 등등)
                    JSONObject weather = jsonObject.getJSONArray("weather").getJSONObject(0);
                    // coord에 위도와 경도 값이 들어있다.
                    JSONObject coord = jsonObject.getJSONObject("coord");
                    //timezone

                    Long updatedAt = jsonObject.getLong("dt");
                    // String updatedAtText = "업데이트 시간: " + new SimpleDateFormat("yyyy/MM/dd hh:mm a", Locale.KOREA).format(new Date(updatedAt * 1000));
                    String temp = main.getString("temp") + "°";
                    String tempMin = main.getString("temp_min") + "°"; //최저기온
                    String tempMax = main.getString("temp_max") + "°"; //최고기온
                    String feelsTemp = main.getString("feels_like") + "°"; //체감온도
                    // String weat = "현재 날씨: " + weather.getString("description");
                    int id = weather.getInt("id");
                    icon = weather.getString("icon");
                    String iconUrl = "http://openweathermap.org/img/w/" + icon + ".png";
                    Log.d("이미지", "onPost" + icon);
                    Log.d("이미지", "onPost2" + iconUrl);
                    Log.d("이미지", "onPost3" + id);


                    //Glide.with().load(iconUrl).into(imageView);

//                String pressure = main.getString("pressure");
//                String humidity = main.getString("humidity");

                    // lat = coord.getDouble("lat");   //가져오는지 참고차 만듬
                    //  lon = coord.getDouble("lon");   //가져오는지 참고차 만듬.
                    GlideApp.with(getApplicationContext()).load("http://openweathermap.org/img/w/" + icon + ".png").override(150, 150).into(imageView);
                    tvResult.setText(temp);
                    tvMaxTemp.setText(tempMax + "/" + tempMin + "체감온도 " + feelsTemp);
                    //tvMinTemp.setText(tempMin);

                    switch (id) {

                        case 200:
                            tvWeather.setText("가벼운 비를 동반한 천둥구름 번개조심,,");
                            break;
                        case 201:
                            tvWeather.setText("비를 동반한 천둥구름");
                            break;
                        case 202:
                            tvWeather.setText("폭우를 동반한 천둥구름");
                            break;
                        case 210:
                            tvWeather.setText("약한 천둥구름");
                            break;
                        case 211:
                            tvWeather.setText("천둥구름");
                            break;
                        case 212:
                            tvWeather.setText("강한 천둥구름");
                            break;
                        case 221:
                            tvWeather.setText("불규칙적인 천둥구름");
                            break;
                        case 230:
                            tvWeather.setText("약한 연기와 안개를 동반한 천둥구름");
                            break;
                        case 231:
                            tvWeather.setText("연기와 안개를 동반한 천둥구름");
                            break;
                        case 300:
                            tvWeather.setText("가벼운 안개비");
                            break;
                        case 301:
                            tvWeather.setText("안개비");
                            break;
                        case 302:
                            tvWeather.setText("강한 안개비");
                            break;
                        case 310:
                            tvWeather.setText("가벼운 이슬비");
                            break;
                        case 311:
                            tvWeather.setText("이슬비");
                            break;
                        case 312:
                            tvWeather.setText("강한 이슬비");
                            break;
                        case 313:
                            tvWeather.setText("소나기와 안개비");
                            break;
                        case 314:
                            tvWeather.setText("강한 소나기와 안개비");
                            break;
                        case 321:
                            tvWeather.setText("소나기");
                            break;
                        case 500:
                            tvWeather.setText("약한 비");
                            break;
                        case 501:
                            tvWeather.setText("평범하게 내리는 비");
                            break;
                        case 502:
                            tvWeather.setText("강한 비");
                            break;
                        case 503:
                            tvWeather.setText("매우 강한 비");
                            break;
                        case 504:
                            tvWeather.setText("극심한 비");
                            break;
                        case 511:
                            tvWeather.setText("우박");
                            break;
                        case 520:
                            tvWeather.setText("약한 소나기 비");
                            break;
                        case 521:
                            tvWeather.setText("소나기 비");
                            break;
                        case 522:
                            tvWeather.setText("강한 소나기 비");
                            break;
                        case 531:
                            tvWeather.setText("불규칙적 소나기 비");
                            break;
                        case 600:
                            tvWeather.setText("가벼운 눈");
                            break;
                        case 601:
                            tvWeather.setText("눈이와요 겨울의 꽃 눈이 옵니다.");
                            break;
                        case 611:
                            tvWeather.setText("진눈깨비");
                            break;
                        case 612:
                            tvWeather.setText("소나기 진눈깨비");
                            break;
                        case 615:
                            tvWeather.setText("약한 비와 눈");
                            break;
                        case 616:
                            tvWeather.setText("비와 눈");
                            break;
                        case 620:
                            tvWeather.setText("약한 소나기 눈");
                            break;
                        case 621:
                            tvWeather.setText("소나기 눈");
                            break;
                        case 622:
                            tvWeather.setText("강한 소나기 눈");
                            break;
                        case 701:
                            tvWeather.setText("연기와 안개");
                            break;
                        case 711:
                            tvWeather.setText("연기 자욱");
                            break;
                        case 721:
                            tvWeather.setText("연기와 안개");
                            break;
                        case 731:
                            tvWeather.setText("모래먼지 황사 마스크 착용!");
                            break;
                        case 741:
                            tvWeather.setText("안개");
                            break;
                        case 751:
                            tvWeather.setText("모래");
                            break;
                        case 761:
                            tvWeather.setText("먼지");
                            break;
                        case 762:
                            tvWeather.setText("화산재 화산재 메이데이메이데이");
                            break;
                        case 771:
                            tvWeather.setText("돌풍 돌풍 ..!!");
                            break;
                        case 781:
                            tvWeather.setText("토네이도 토네이도 외출금지!!");
                            break;
                        case 800:
                            tvWeather.setText("구름 한 점 없는 맑은 하늘");
                            break;
                        case 801:
                            tvWeather.setText("약간의 구름 낀 하늘");
                            break;
                        case 802:
                            tvWeather.setText("드문드문 구름이 낀 하늘");
                            break;
                        case 803:
                            tvWeather.setText("구름이 거의 없는 하늘");
                            break;
                        case 804:
                            tvWeather.setText("구름으로 뒤덮인 흐린 하늘");
                            break;
                        case 900:
                            tvWeather.setText("토네이도 토네이도  외출금지!!");
                            break;
                        case 901:
                            tvWeather.setText("태풍 주의 태풍 주의 외출 자제");
                            break;
                        case 902:
                            tvWeather.setText("허리케인 허리케인 조심 조심!");
                            break;
                        case 903:
                            tvWeather.setText("한랭 한랭 날씨 추움.");
                            break;
                        case 904:
                            tvWeather.setText("고온 고온 더워 고온 적당히 얇게 입으세요!");
                            break;
                        case 905:
                            tvWeather.setText("바람이 부는 날씨");
                            break;
                        case 906:
                            tvWeather.setText("우박 우박 조심");
                            break;
                        case 951:
                            tvWeather.setText("바람이 거의 없습니다.");
                            break;
                        case 955:
                            tvWeather.setText("신선한 바람");
                            break;
                        case 956:
                            tvWeather.setText("강한 바람");
                            break;
                        case 957:
                            tvWeather.setText("돌풍에 가까운 센 바람");
                            break;
                        case 958:
                            tvWeather.setText("돌풍");
                            break;
                        case 959:
                            tvWeather.setText("심각한 돌풍 외출금지");
                            break;
                        case 960:
                            tvWeather.setText("폭풍");
                            break;
                        case 961:
                            tvWeather.setText("강한 폭풍");
                            break;
                        case 962:
                            tvWeather.setText("허리케인 조심조심!");
                            break;

                    }

                    //tvWeather.setText(weat);
                    //Log.d("TAG", );

                    Log.d("테스트", "onPostExecute" + temp);
                    // Log.d("테스트", "onPostExecute" + lat+lon);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }


    //------------------------------------------------------------------------------gps---------------------------------------------------

    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;


            // 모든 퍼미션을 허용했는지 체크합니다.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if (check_result) {

                //위치 값을 가져올 수 있음
                ;
            } else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {

                    Toast.makeText(MainActivity.this, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                    finish();


                } else {

                    Toast.makeText(MainActivity.this, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();

                }
            }

        }
    }

    void checkRunTimePermission() {

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            // 3.  위치 값을 가져올 수 있음


        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(MainActivity.this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MainActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MainActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

    }

    public String getCurrentAddress(double latitude, double longitude) {

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    7);
        } catch (IOException ioException) {
            //네트워크 문제
            Log.d("인터넷", "getCurrentAddress" + ioException);
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }


        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        }

        Address address = addresses.get(0);
        return address.getAddressLine(0).toString() + "\n";

    }

    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                        checkRunTimePermission();
                        return;
                    }
                }

                break;
        }
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

}

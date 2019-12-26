package com.example.user.quickdustinfo.util;

import com.example.user.quickdustinfo.model_dust_metarial.FineWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


public interface FineDustApi {
    String BASE_URL = "http://newsky2.kma.go.kr/";

    @Headers("appKey:4PSPA62Mj635v5hVjseQram%2BxcBx4xcgCS%2FS0xo5DHXxllVBEK1havsU3kbr0mBzXXrmWNY5cZF3zvo%2BiV1Pgw%3D%3D")
    @GET("service/SecndSrtpdFrcstInfoService2/ForecastSpaceData?SrtpdFrcstInfoService2/ForecastSpaceData?serviceKey=4PSPA62Mj635v5hVjseQram%2BxcBx4xcgCS%2FS0xo5DHXxllVBEK1havsU3kbr0mBzXXrmWNY5cZF3zvo%2BiV1Pgw%3D%3D&base_date=20191210&base_time=0500")
    Call<FineWeather> getFineWeather(@Query("nx") double latitude,
                                     @Query("ny") double longitude);
}



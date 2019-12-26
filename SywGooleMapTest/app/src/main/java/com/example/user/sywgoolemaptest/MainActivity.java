package com.example.user.sywgoolemaptest;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

//맵받을준비됬으면 내가너한테 던질게 ~
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {


    //조심할것 ! 밑줄 그어진 것으로 임포트 해야함! (android.app)
    private FragmentManager fragmentManager;
    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager=getFragmentManager();
        mapFragment=(MapFragment) fragmentManager.findFragmentById(R.id.fgGoogleMap);
        mapFragment.getMapAsync(this); //둘이가 비동기화 식으로 매치가된다.
    }

    //구글맵이 준비되어있으면 이 함수를 불러줄게 그리고 거기다가 구글맵을 던져줄게 받아가~ 이말이다
    @Override
    public void onMapReady(GoogleMap googleMap) {
        //신방빌딩
        LatLng location = new LatLng(37.562169, 127.035177);
        //좌표위에 표시될 마킹할 부분을 선택해서 붙여놓는다.
        MarkerOptions markerOptions= new MarkerOptions();
        markerOptions.title("미래능력개발교육원");
        markerOptions.snippet("안드로이드8기 머물던곳");
        markerOptions.position(location);
        googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,16)); //줌인 줌아웃 1부터 24단계까지있다.


    }
}

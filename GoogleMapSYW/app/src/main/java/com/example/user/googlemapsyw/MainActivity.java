package com.example.user.googlemapsyw;

        import android.app.FragmentManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.Toast;

        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.MapFragment;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.model.BitmapDescriptorFactory;
        import com.google.android.gms.maps.model.GroundOverlayOptions;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.MarkerOptions;

        import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap gMap;
    private GroundOverlayOptions videoMark;
    private ArrayList<GroundOverlayOptions> cctvList = new ArrayList<GroundOverlayOptions>();
    private FragmentManager fragmentManager;
    private MapFragment mapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();

        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.fgGoogleMap); //동일
        mapFragment.getMapAsync(this); //동일

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-26.203222, 28.048287), 15));
        gMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                videoMark = new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.plag)).position(latLng, 100f, 100f);
                cctvList.add(videoMark);
                for (int i = 0; i < cctvList.size(); i++) {
                    gMap.addGroundOverlay(cctvList.get(i));
                }
            }
        });
//        gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {       //구글맵 두번째 테스트 월드컵바로가기
//            @Override
//            public void onMapClick(LatLng point) {
//                videoMark = new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.plag)).position(point,100f,100f);
//                gMap.addGroundOverlay(videoMark);
//            }
//        });
        //==============================================================================
//        LatLng location = new LatLng(-26.203222, 28.048287);
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.title("남아공");
//        markerOptions.snippet("요하네스 버그");                 구글맵 첫번째 테스트
//        markerOptions.position(location);
//        googleMap.addMarker(markerOptions);
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
//        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,16));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "위성지도");
        menu.add(0, 2, 0, "일반지도");
        //menu.add(0,3,0,"월드컵경기장 바로가기");
        menu.add(0, 3, 0, "바로전 CCTV지우기");
        menu.add(0, 4, 0, "모든 CCTV 지우기");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                //위성지도
                gMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case 2:
                //일반지도
                gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case 3:
                //지정장소 바로가깃
//                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-26.203222, 28.048287),15));

                //CCTV바로전 지우기
                if (cctvList.size() == 0) {
                    Toast.makeText(getApplicationContext(), "지울 마커 음슴", Toast.LENGTH_SHORT).show();
                    return false;
                }
                gMap.clear();
                cctvList.remove(cctvList.size() - 1);
                for (int i = 0; i < cctvList.size(); i++) {
                    gMap.addGroundOverlay(cctvList.get(i));
                }
                return true;
            case 4:
                gMap.clear();
                cctvList.removeAll(cctvList);
                return true;

        }
        return false;
    }
}

package com.example.user.navigation_fragment_test;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    DrawerLayout mainDrawerLayout;
    LinearLayout drawerMenu;
    Button btnOpen, btnClose, btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpen = findViewById(R.id.btnOpen);
        btnClose = findViewById(R.id.btnClose);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        drawerMenu = findViewById(R.id.drawerMenu);
        mainDrawerLayout = findViewById(R.id.mainDrawerLayout);

        btnOpen.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        drawerMenu.setOnTouchListener(this);
        mainDrawerLayout.setDrawerListener(listener);
    }

    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {

        //슬라이딩을 시작했을때 이벤트 발생
        @Override
        public void onDrawerSlide(@NonNull View view, float v) {
            Toast.makeText(getApplicationContext(), "슬라이드 했어요", Toast.LENGTH_LONG).show();
        }

        //메뉴가 열렸을때 이벤트 발생
        @Override
        public void onDrawerOpened(@NonNull View view) {

        }

        //메뉴가 닫았을때 이벤트 발생
        @Override
        public void onDrawerClosed(@NonNull View view) {
            Toast.makeText(getApplicationContext(), "메뉴를 닫았어요 :D", Toast.LENGTH_LONG).show();
        }

        //메뉴바 상태가 바뀌었을때 이벤트 발생
        @Override
        public void onDrawerStateChanged(int i) {

        }
    };

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragmentActivity = null;
        switch (v.getId()) {
            case R.id.btnOpen:
                mainDrawerLayout.openDrawer(drawerMenu);
                break;
            case R.id.btnClose:
                mainDrawerLayout.closeDrawer(drawerMenu);
                finish();
                break;
            case R.id.btn1:
                fragmentActivity = new Fragment1Activity();
                ft.replace(R.id.frameLayout,fragmentActivity);
                Toast.makeText(getApplicationContext(),"dd",Toast.LENGTH_SHORT).show();
                ft.commit();
                break;
            case R.id.btn2:
                fragmentActivity = new Fragment2Activity();
                ft.replace(R.id.frameLayout,fragmentActivity);
                ft.commit();
                break;
            case R.id.btn3:
                fragmentActivity = new Fragment3Activity();
                ft.replace(R.id.frameLayout,fragmentActivity);
                ft.commit();
                break;
        }

    }

    //drawmenu를 터치했을때 발생하는 이벤트 구현
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return true;
    }
}
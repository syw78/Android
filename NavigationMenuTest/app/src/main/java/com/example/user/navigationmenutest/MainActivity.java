package com.example.user.navigationmenutest;


import android.support.annotation.NonNull;
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
    Button btnOpen, btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpen = findViewById(R.id.btnOpen);
        btnClose = findViewById(R.id.btnClose);
        drawerMenu = findViewById(R.id.drawerMenu);
        mainDrawerLayout = findViewById(R.id.mainDrawerLayout);

        btnOpen.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        drawerMenu.setOnTouchListener(this);
        mainDrawerLayout.setDrawerListener(listener);
    }

    DrawerLayout.DrawerListener listener=new DrawerLayout.DrawerListener(){

        //슬라이딩을 시작했을때 이벤트 발생
        @Override
        public void onDrawerSlide(@NonNull View view, float v) {
            Toast.makeText(getApplicationContext(),"슬라이드 했어요",Toast.LENGTH_LONG).show();
        }
        //메뉴가 열렸을때 이벤트 발생
        @Override
        public void onDrawerOpened(@NonNull View view) {

        }
        //메뉴가 닫았을때 이벤트 발생
        @Override
        public void onDrawerClosed(@NonNull View view) {
            Toast.makeText(getApplicationContext(),"메뉴를 닫았어요 :D",Toast.LENGTH_LONG).show();
        }
        //메뉴바 상태가 바뀌었을때 이벤트 발생
        @Override
        public void onDrawerStateChanged(int i) {

        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOpen:
                mainDrawerLayout.openDrawer(drawerMenu);
                break;
            case R.id.btnClose:
                mainDrawerLayout.closeDrawer(drawerMenu);
                finish();
                break;
        }
    }
    //drawmenu를 터치했을때 발생하는 이벤트 구현
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return true;
    }
}
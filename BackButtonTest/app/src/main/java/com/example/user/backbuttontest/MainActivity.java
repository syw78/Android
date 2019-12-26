package com.example.user.backbuttontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private long backButtonTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    //백버튼 누르면 나가지는 것
    @Override
    public void onBackPressed() { //시스템에서 주는 타임
        long currentTime = System.currentTimeMillis(); //1970년대 부터 지금까지의 시간을  밀리초로 계산해서 주는거
        long gapTime = currentTime - backButtonTime;
        if (gapTime >= 0 && gapTime <= 2000) {
            super.onBackPressed();

        } else {
            backButtonTime=currentTime;
            Toast.makeText(getApplicationContext(),"2초이내 두번누르면 나감 주의바람."+currentTime,Toast.LENGTH_SHORT).show();
        }


    }
}

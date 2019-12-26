package com.example.user.myservicemusictest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnStart,btnStop;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);           //서비스도 액티비티 만들어야한다!( 액티비티는 액티비틴데 보이지않음.)

        btnStart=findViewById(R.id.btnStart);
        btnStop=findViewById(R.id.btnStop);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //서비스를 불러야함 그래서 인텐트!
        switch (view.getId()){
            case R.id.btnStart:
                intent = new Intent(getApplicationContext(),MusicService.class);
                startService(intent); //얘는 액티비티는 맞는데 서비스니까 스타트서비스로 해줘야한다.
                break;

            case R.id.btnStop:
                stopService(intent); //이걸로하면 정지가됨

                break;
        }

    }
}

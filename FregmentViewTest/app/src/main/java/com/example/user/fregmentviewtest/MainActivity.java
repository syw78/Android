package com.example.user.fregmentviewtest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btnMenu1, btnMenu2, btnMenu3, btnMenu4;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMenu1 = findViewById(R.id.btnMenu1);
        btnMenu2 = findViewById(R.id.btnMenu2);
        btnMenu3 = findViewById(R.id.btnMenu3);
        btnMenu4 = findViewById(R.id.btnMenu4);

        btnMenu1.setOnClickListener(this);
        btnMenu2.setOnClickListener(this);
        btnMenu3.setOnClickListener(this);
        btnMenu4.setOnClickListener(this);

        //맨먼저 화면을 띄어주는 기능
        btnMenu1.callOnClick();
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragmentActivity = null;
        switch (view.getId()) {
            case R.id.btnMenu1:
                fragmentActivity = new Fragment1Activity();
                //프레이밍 레이아웃 대체하겠다.

                break;
            case R.id.btnMenu2:
                fragmentActivity = new Fragment2Activity();
                break;
            case R.id.btnMenu3:
                fragmentActivity = new Fragment3Activity();
                break;
            case R.id.btnMenu4:
                fragmentActivity = new Fragment4Activity();
                break;
        }
        ft.replace(R.id.frameLayout, fragmentActivity);
        ft.commit();
    }
}

package com.example.user.systemmenutest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button btnMenu;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //모든것을 객체화시켜줌  그래서 밑에랑 순서바꾸면 못찾음
        btnMenu = findViewById(R.id.btnMenu);
        linearLayout = findViewById(R.id.linearLayout);

    }

    //1.시스템옵션메뉴선택 메뉴를 인플레이트하고 옵션메뉴에 등록한다.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();  //인플레터 객체를 만들겠다 그런기능
        menuInflater.inflate(R.menu.option_menu, menu); //그 인플레터를 인플레이트 하겠다
        return true;
    }

    //2.옵션메뉴를 클릭을하면 인식을 하는 함수.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemBlue:
                linearLayout.setBackgroundColor(Color.BLUE);
                break;

            case R.id.itemGreen:
                linearLayout.setBackgroundColor(Color.GREEN);
                break;
            case R.id.itemRed:
                linearLayout.setBackgroundColor(Color.RED);
                break;
            case R.id.subRotate:
                btnMenu.setRotation(45);
                break;
            case R.id.subSize:
                btnMenu.setScaleX(2);
                break;
            default:
                break;
        }
        return true;
    }
}

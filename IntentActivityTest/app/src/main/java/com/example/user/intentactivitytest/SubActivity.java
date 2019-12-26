package com.example.user.intentactivitytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnBack;
    int number1=0, number2=0, sum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        //getintent 속에 값이 들어있음;
        Intent intent =getIntent();
        number1 = intent.getIntExtra("number1",0);
        number2 = intent.getIntExtra("number2",0);


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        sum = number1 + number2;
        intent.putExtra("sum",sum); //값을 넣는다.
        setResult(1001,intent); //값을 돌려준다. 메인에
        finish();
    }
}
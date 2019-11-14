package com.example.user.exercise_jic10_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnBack;
    int num1=0,num2=0,sum=0,sub=0,divi=0,multi=0;
    String calc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        btnBack=findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        Intent intent = getIntent();
        num1=intent.getIntExtra("num1",0);
        num2=intent.getIntExtra("num2",0);
        calc=intent.getStringExtra("calc");
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        sum = num1 + num2;
        sub = num1 - num2;
        divi= num1 / num2;
        multi= num1*num2;
        if(calc.equals("add")){
            intent.putExtra("result",sum);
            setResult(1001,intent);
            finish();
        }else if(calc.equals("sub")){
            intent.putExtra("result",sub);
            setResult(1001,intent);
            finish();
        }else if(calc.equals("mul")){
            intent.putExtra("result",multi);
            setResult(1001,intent);
            finish();
        }else if(calc.equals("div")){
            intent.putExtra("result",divi);
            setResult(1001,intent);
            finish();
        }


    }
}

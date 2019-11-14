package com.example.user.exercise_jic10_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RadioButton btnSecond,btnThird;
    Button btnNew;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSecond=findViewById(R.id.btnSecond);
        btnThird=findViewById(R.id.btnThird);
        btnNew=findViewById(R.id.btnNew);
        radioGroup=findViewById(R.id.radioGroup);

        btnNew.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(btnSecond.isChecked()){
            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
            startActivityForResult(intent,1000);
            radioGroup.clearCheck();
        }else if(btnThird.isChecked()){
            Intent intent = new Intent(MainActivity.this,ThirdActivity.class);
            startActivityForResult(intent,1000);
            radioGroup.clearCheck();
        }else{
            Toast.makeText(getApplicationContext(),"체크해주세요",Toast.LENGTH_SHORT).show();
            return;
        }
    }
}

package com.example.user.intentactivitytest;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnOpen;
    EditText editText, editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpen = findViewById(R.id.btnOpen);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        btnOpen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //명시적 인텐트, 값을 전달하지 않음
        Intent intent = new Intent(MainActivity.this,SubActivity.class);

        //명시적 인텐트, 값을 전달함
        intent.putExtra("number1",Integer.parseInt(editText.getText().toString()));
        intent.putExtra("number2",Integer.parseInt(editText2.getText().toString()));


        startActivityForResult(intent,1000);
    }

    //상대방이 액티비티 값을 다시 돌려주었을때 감지하는콜백함수
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent returnIntent) {
        super.onActivityResult(requestCode, resultCode, returnIntent);
        if(requestCode ==1000 && resultCode ==1001) {

            int sum = returnIntent.getIntExtra("sum", 0);
            Toast.makeText(getApplicationContext(), "합계" + sum, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"이상해요",Toast.LENGTH_SHORT).show();

        }



    }


}
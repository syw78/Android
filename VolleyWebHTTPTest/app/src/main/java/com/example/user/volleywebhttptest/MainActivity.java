package com.example.user.volleywebhttptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvID,tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvID=findViewById(R.id.tvID);
        tvPassword=findViewById(R.id.tvPassword);

        //로그인이 완료하면 이 화면을 불러줌(ID,Password)
        //회원가입 완료하면 이 화면을 불러줌(ID,Password)
        Intent intent = getIntent();
        String userID=intent.getStringExtra("userID");
        String userPassword=intent.getStringExtra("userPassword");

        tvID.setText(userID);
        tvPassword.setText(userPassword);


    }
}

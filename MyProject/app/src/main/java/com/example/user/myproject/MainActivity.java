package com.example.user.myproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //외부에서 값이 들어올때
        setContentView(R.layout.activity_main);
    }
}

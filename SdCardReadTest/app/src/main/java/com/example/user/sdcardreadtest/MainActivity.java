package com.example.user.sdcardreadtest;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRead;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead= findViewById(R.id.btnRead);
        editText=findViewById(R.id.editText);

        //사용자에게 요구허락 요청을 진행한다.
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
        btnRead.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        try {
            FileInputStream fis = new FileInputStream("/sdcard/raw_test.txt");
            byte[] readData = new byte[fis.available()];
            fis.read(readData);
            editText.setText(new String(readData));
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

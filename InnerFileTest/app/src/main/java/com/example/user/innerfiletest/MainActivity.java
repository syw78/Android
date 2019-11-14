package com.example.user.innerfiletest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnMemoryWrite, btnMemoryRead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMemoryRead=findViewById(R.id.btnMemoryRead);
        btnMemoryWrite=findViewById(R.id.btnMemoryWrite);

        //내부메모리에 파일을 쓰기 기능
        btnMemoryWrite.setOnClickListener(this);
        //내부메모리에 파일을 읽기 기능
        btnMemoryRead.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnMemoryWrite:
                fileInnerWrite();
                break;
            case R.id.btnMemoryRead:
                fileInnerRead();
                break;
        }

    }

    //내부메모리에 파일을 쓰기 기능
    private void fileInnerWrite() {
        try {
            FileOutputStream fos=openFileOutput("fike.txt",Context.MODE_PRIVATE);
            String string = "쿡북 안드로이드";
            fos.write(string.getBytes());
            fos.close();
            toastDisplay("file.txt가 생성되었음");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //내부메모리에 파일을 읽기 기능

    private void fileInnerRead() {
        FileInputStream fis;
        try {
             fis = openFileInput("file.txt");
            byte[] data = new byte[fis.available()];
            fis.read(data);
            toastDisplay(new String(data));
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    private void toastDisplay(String message) {
        Toast.makeText(MainActivity.this,"message",Toast.LENGTH_SHORT).show();
    }
}

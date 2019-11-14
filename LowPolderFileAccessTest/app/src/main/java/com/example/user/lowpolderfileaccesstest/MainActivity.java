package com.example.user.lowpolderfileaccesstest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnRead,btnStart;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRead=findViewById(R.id.btnRead);
        btnStart=findViewById(R.id.btnStart);
        editText=findViewById(R.id.editText);

        btnRead.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRead:
                rawReadFile();
                break;
        }
    }

    private void rawReadFile() {
        try {
            InputStream is =getResources().openRawResource(R.raw.raw_test); //인풋스트림 가져오는거
            byte[] txt= new byte[is.available()];
            is.read(txt);
            editText.setText(new String(txt));
            is.close();
        } catch (IOException e) {

        }


    }
}

package com.example.user.custommakeimageviewtest;


import android.Manifest;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnPrevious, btnNext;
    MyPictureView myPictureView;
    File[] imageFiles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        btnPrevious=findViewById(R.id.btnPrevious);
        btnNext=findViewById(R.id.btnNext);
        myPictureView=findViewById(R.id.myPictureView);

        //맨 처음 화면을 보여줘야 한다.

        imageFiles=new File(Environment.getExternalStorageDirectory().
                getAbsolutePath()+"/picc").listFiles();

        myPictureView.setSrc(imageFiles[0].toString().trim());
        myPictureView.invalidate();

        btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}

package com.example.user.exercise_jic8_2;

import android.Manifest;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnBefore,btnAfter;
    EditText edtText;
    MyPictureView myPictureView;
    File[] imageFiles;
    int currentPoint=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBefore=findViewById(R.id.btnBefore);
        btnAfter=findViewById(R.id.btnAfter);
        edtText=findViewById(R.id.edtText);
        myPictureView=findViewById(R.id.myPictureView);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        imageFiles= new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/picc").listFiles();
        myPictureView.setSrc(imageFiles[currentPoint].toString().trim());
        myPictureView.invalidate();

        edtText.setText((currentPoint+1)+"/"+imageFiles.length);
        toastPlay((currentPoint+1)+"번째 사진입니다");
        btnBefore.setOnClickListener(this);
        btnAfter.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBefore:
                imageChangeBefore();
                break;
            case R.id.btnAfter:
                imageChangeAfter();
                break;
        }


    }

    private void imageChangeAfter() {
        currentPoint+=1;
        currentPoint=(currentPoint>4)?(0):(currentPoint);
        edtText.setText((currentPoint+1)+"/"+imageFiles.length);
        toastPlay((currentPoint+1)+"번째 사진입니다");
        myPictureView.setSrc(imageFiles[currentPoint].toString());
        myPictureView.invalidate();
    }

    private void imageChangeBefore() {
        currentPoint-=1;
        currentPoint=(currentPoint<0)?(4):(currentPoint);
        edtText.setText((currentPoint+1)+"/"+imageFiles.length);
        toastPlay((currentPoint+1)+"번째 사진입니다");
        myPictureView.setSrc(imageFiles[currentPoint].toString());
        myPictureView.invalidate();
    }
    private void toastPlay(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }



}

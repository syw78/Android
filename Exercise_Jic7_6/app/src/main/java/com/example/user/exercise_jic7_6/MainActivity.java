package com.example.user.exercise_jic7_6;

import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    RadioButton rbo1,rbo2,rbo3;
    Button btnG;
    View dialogView;
    ImageView image1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rbo1=findViewById(R.id.rbo1);
        rbo2=findViewById(R.id.rbo2);
        rbo3=findViewById(R.id.rbo3);
        btnG=findViewById(R.id.btnG);

        btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogView=View.inflate(MainActivity.this,R.layout.picture,null);
                final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setView(dialogView);
                image1=dialogView.findViewById(R.id.image1);
                if(rbo1.isChecked()){

                    dialog.setTitle(rbo1.getText().toString());
                    image1.setImageResource(R.drawable.fish2);
                }else if(rbo2.isChecked()){
                    dialog.setTitle(rbo2.getText().toString());
                    image1.setImageResource(R.drawable.house);
                }else{
                    dialog.setTitle(rbo3.getText().toString());
                    image1.setImageResource(R.drawable.cat);
                }
                dialog.setPositiveButton("닫기",null);
                dialog.show();
            }
        });
    }
}

package com.example.user.nugaandoreo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //res 에서 drawble 에서 오른쪽 클릭하고 사진 붙여넣는다
    EditText editMessage;
    Button btnShow;
    Button btnHome;
    RadioButton rboCook;
    RadioButton rboFish;
    ImageView ivShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editMessage=findViewById(R.id.editMessage);
        btnHome=findViewById(R.id.btnHome);
        btnShow=findViewById(R.id.btnShow);
        rboCook=findViewById(R.id.rboCook);
        rboFish=findViewById(R.id.rboFish);
        ivShow=findViewById(R.id.ivShow);
        setTitle("좀 그럴듯한 응용프로그램");

        //글자나타내기
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message =editMessage.getText().toString().trim();
                toastDisplay("안녕안녕");
            }
        });

        //홈피열기
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message ="http://"+editMessage.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(message));
                startActivity(intent);
            }
        });


        rboCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivShow.setImageResource(R.drawable.cook); //안드에서 res(리소스)는 무조건 R 그래서 R.블라블라블라

            }
        });
        rboFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivShow.setImageResource(R.drawable.fish4);
            }
        });


    }// end of onCreate

    public void  toastDisplay(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show(); //토스트창
    }
}

package com.example.user.exercise_jic4_4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Switch switch1;
    TextView textView2;
    RadioButton rboR;
    RadioButton rboM;
    RadioButton rboN;
    ImageView imageView;
    Button btnExit;
    Button btnBefore;
    LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("안드 버전 초이스");

        switch1 = findViewById(R.id.switch1);
        textView2 = findViewById(R.id.textView2);
        rboR= findViewById(R.id.rboR);
        rboM= findViewById(R.id.rboM);
        rboN= findViewById(R.id.rboN);
        imageView = findViewById(R.id.imageView);
        btnExit = findViewById(R.id.btnExit);
        btnBefore=findViewById(R.id.btnBefore);
        layout = findViewById(R.id.layout);

        layout.setVisibility(View.INVISIBLE);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(switch1.isChecked()==true){
                    layout.setVisibility(View.VISIBLE);
                }else{
                    layout.setVisibility(View.INVISIBLE);
                }
            }
        });

        rboR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setImageResource(R.drawable.roll);
            }
        });
        rboM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setImageResource(R.drawable.ma);
            }
        });
        rboN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setImageResource(R.drawable.nuga);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch1.setChecked(false);
                layout.setVisibility(View.INVISIBLE);
            }
        });

    }
}

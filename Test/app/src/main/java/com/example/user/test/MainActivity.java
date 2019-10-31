package com.example.user.test;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnNate;
    Button btnCall;
    Button btnGa;
    Button btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNate=findViewById(R.id.btnNate);
        btnCall=findViewById(R.id.btnCall);
        btnGa=findViewById(R.id.btnGa);
        btnExit=findViewById(R.id.btnExit);

        btnNate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //인텐트 기술
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://m.nate.com"));
                startActivity(intent);
            }
        });


        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //인텐트 기술
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:/010-4678-6205"));
                startActivity(intent);
            }
        });

        btnGa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //인텐트 기술
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("content://media/internal/images/media"));
                startActivity(intent);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //인텐트 기술
              finish();
            }
        });
    }
}

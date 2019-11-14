package com.example.user.impliciteintenttest;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnDial, btnHomepage, btnGoogleMap, btnGoogleSearch, btnSMS, btnPhoto,btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity","onCreate()");


        btnDial = findViewById(R.id.btnDial);
        btnHomepage = findViewById(R.id.btnHomepage);
        btnGoogleMap = findViewById(R.id.btnGoogleMap);
        btnGoogleSearch = findViewById(R.id.btnGoogleSearch);
        btnSMS = findViewById(R.id.btnSMS);
        btnPhoto = findViewById(R.id.btnPhoto);
        btnExit=findViewById(R.id.btnExit);

        btnDial.setOnClickListener(this);
        btnHomepage.setOnClickListener(this);
        btnGoogleMap.setOnClickListener(this);
        btnGoogleSearch.setOnClickListener(this);
        btnSMS.setOnClickListener(this);
        btnPhoto.setOnClickListener(this);
        btnExit.setOnClickListener(this);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity","onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity","onstart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity","onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity","onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity","onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity","onDestroy()");
    }

    @Override
    public void onClick(View view) {
        Uri uri = null;
        Intent intent = null;

        switch (view.getId()) {
            case R.id.btnDial:
                uri = Uri.parse("tel:010-3201-1426");
                intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                break;
            case R.id.btnHomepage:
                uri = Uri.parse("http://www.naver.com");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.btnGoogleMap:
                uri = Uri.parse("http://maps.google.com/maps?q=" + 37.576314 + "," + 127.039082);
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.btnGoogleSearch:  //서치는 무조건 크롬으로 한다 생각해라
                intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY,"왕십리 순대국밥");
                startActivity(intent);
                break;
            case R.id.btnSMS:
                intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra("sms body","Test");
                intent.setData(Uri.parse("smsto:"+Uri.encode("010-8256-4933")));
                startActivity(intent);
                break;
            case R.id.btnPhoto:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
                break;
            case R.id.btnExit:
                finish();
                break;
        }
    }


}

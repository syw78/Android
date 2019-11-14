package com.example.user.myfragmentviewtest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnColor, btnToast,btnImage,btnSite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnColor= findViewById(R.id.btnColor);
        btnToast=findViewById(R.id.btnToast);
        btnImage=findViewById(R.id.btnImage);
        btnSite=findViewById(R.id.btnSite);

        btnColor.setOnClickListener(this);
        btnToast.setOnClickListener(this);
        btnImage.setOnClickListener(this);
        btnSite.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragmentActivity=null;

        switch (view.getId()){
            case R.id.btnColor:
                fragmentActivity = new Fragement1Activity();
                break;
            case R.id.btnToast:
                fragmentActivity = new Fragement2Activity();
                break;
            case R.id.btnImage:
                fragmentActivity = new Fragement3Activity();
                break;
            case R.id.btnSite:
                fragmentActivity = new Fragement4Activity();
                break;
        }
        ft.replace(R.id.frameLayout,fragmentActivity);
        ft.commit();
    }
}

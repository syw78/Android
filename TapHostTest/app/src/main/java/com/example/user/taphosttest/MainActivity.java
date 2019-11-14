package com.example.user.taphosttest;

import android.app.TabActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
   TabHost tabHost;
   TabHost.TabSpec tabScreen1, tabScreen2,tabScreen3,tabScreen4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tabHost를 가져와야 한다.
        tabHost=getTabHost();

        //2.tabWidget에 제목을 표시할려고한다.
        tabScreen1=tabHost.newTabSpec("SCREEN1").setIndicator("화면1");
        tabScreen1.setContent(R.id.screen1);
        tabHost.addTab(tabScreen1);

        tabScreen2=tabHost.newTabSpec("SCREEN2").setIndicator("화면2");
        tabScreen2.setContent(R.id.screen2);
        tabHost.addTab(tabScreen2);

        tabScreen3=tabHost.newTabSpec("SCREEN3").setIndicator("화면3");
        tabScreen3.setContent(R.id.screen3);
        tabHost.addTab(tabScreen3);

        tabScreen4=tabHost.newTabSpec("SCREEN4").setIndicator("화면4");
        tabScreen4.setContent(R.id.screen4);
        tabHost.addTab(tabScreen4);

        tabHost.setCurrentTab(0);

    }
}

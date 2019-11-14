package com.example.user.exercise_jic6_3;

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
        tabScreen1=tabHost.newTabSpec("a").setIndicator("커피");
        tabScreen1.setContent(R.id.iv1);
        tabHost.addTab(tabScreen1);

        tabScreen2=tabHost.newTabSpec("맥주").setIndicator("맥주");
        tabScreen2.setContent(R.id.iv2);
        tabHost.addTab(tabScreen2);

        tabScreen3=tabHost.newTabSpec("햄벅").setIndicator("햄벅");
        tabScreen3.setContent(R.id.iv3);
        tabHost.addTab(tabScreen3);

        tabScreen4=tabHost.newTabSpec("피자").setIndicator("피자");
        tabScreen4.setContent(R.id.iv4);
        tabHost.addTab(tabScreen4);

        tabHost.setCurrentTab(0); //맨처음 화면을 설정하는것

    }
}

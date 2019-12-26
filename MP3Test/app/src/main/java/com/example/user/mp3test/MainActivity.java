package com.example.user.mp3test;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private Switch switchMusic;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchMusic=findViewById(R.id.switchMusic);
        //음악을 들으려면 곡을 선택한  미디어플레이어 객체를 만들어야한다.
        mediaPlayer= MediaPlayer.create(this,R.raw.band);
        switchMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchMusic.isChecked()==true){
                    mediaPlayer.start();
                }else{
                    mediaPlayer.stop();
                }
            }
        });
    }
}

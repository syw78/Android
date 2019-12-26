package com.example.user.mymp3playertest;


import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listViewMP3;
    private Button btnPlay, btnPause;
    private TextView textView;
    private ProgressBar progressBar;

    private ArrayList<String> mp3list = new ArrayList<>();
    private MediaPlayer mediaPlayer;
    private String selectMP3;

    //핸드폰에 있는 sdcard 위치를 나타냄
    static final private String MP3_PATH = "/sdcard/musicc/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewMP3 = findViewById(R.id.listViewMP3);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);


        //외부장치를 권한 설정 요청
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        //외부 디렉토리에있는 파일 및 디렉토리 모두를 배열로 가져오는 방법
        File[] listFiles = new File(MP3_PATH).listFiles();
        for (File file : listFiles) {
            String fileName = file.getName(); // 파일명 또는 디렉토리 명이다.
            String extendName = fileName.substring(fileName.length() - 3);
            if (extendName.equals("mp3") || extendName.equals("mp4")) {
                mp3list.add(fileName);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, mp3list);
        listViewMP3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewMP3.setAdapter(adapter);
        listViewMP3.setItemChecked(0, true);

        listViewMP3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectMP3 = mp3list.get(position);

            }
        });


        wandooSettingInit(true, false, View.INVISIBLE);
        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlay:
                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(MP3_PATH + selectMP3);
                    //외부파일에서 가져오기 위해서 prepare 진행 해야한다.
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    wandooSettingInit(false, true, View.VISIBLE);
                    textView.setText("실행중인 음악명 : " + selectMP3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnPause:
                mediaPlayer.stop();
                mediaPlayer.reset();
                wandooSettingInit(true,false,View.INVISIBLE);
                textView.setText("실행 할 음악을 선택하세요. ");
                break;
        }

    }

    private void wandooSettingInit(boolean b, boolean b1, int visible) {
        btnPlay.setClickable(b);
        btnPause.setClickable(b1);
        progressBar.setVisibility(View.VISIBLE);
    }
}
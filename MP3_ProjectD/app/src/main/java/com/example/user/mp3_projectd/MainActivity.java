package com.example.user.mp3_projectd;

import android.Manifest;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.SeekBar;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageButton btnSearch, btnRewind,btnPlay,btnForward;
    ArrayList<ItemData> list = new ArrayList<ItemData>();
    ArrayList<String> list2 = new ArrayList<String>();
    LinearLayoutManager linearLayoutManager;
    MainAdapter mainAdapter;
    SeekBar pbMP3;
    static MyDBHelper myDBHelper;
    static SQLiteDatabase sqLiteDatabase;
    MediaPlayer mediaPlayer;
    static final String MP3_PATH="/sdcard/musicc/";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        pbMP3=findViewById(R.id.pbMP3);
        myDBHelper=new MyDBHelper(this);
        list.add(new ItemData())
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);


        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        mainAdapter=new MainAdapter(R.layout.list_item,list);
        recyclerView.setAdapter(mainAdapter);

    }
}

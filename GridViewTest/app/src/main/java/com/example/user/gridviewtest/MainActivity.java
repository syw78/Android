package com.example.user.gridviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    ArrayList<MyData> list = new ArrayList<MyData>();
    MyGridAdapter myGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("영화 포스터");
        gridView=findViewById(R.id.gridView);
        listInsertData();
       myGridAdapter = new MyGridAdapter(this,R.layout.image_view_layout,list);
       gridView.setAdapter(myGridAdapter);
    }

    private void listInsertData() {
        Integer[] posterID = {R.drawable.mov01,R.drawable.mov03,R.drawable.mov05,R.drawable.mov12,R.drawable.mov15,R.drawable.mov18,
                R.drawable.mov23,R.drawable.mov35,R.drawable.mov49,R.drawable.mov55};
        for(int i=0;i<30;i++){
            list.add(new MyData(posterID[(int)(Math.random()*(10))]));
        }
    }
}

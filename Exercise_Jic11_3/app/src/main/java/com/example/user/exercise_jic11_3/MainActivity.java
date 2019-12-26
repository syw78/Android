package com.example.user.exercise_jic11_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("스피너테스트");
        spinner1=findViewById(R.id.spinner1);
        final String[] movie={"써니","완득이","괴물","라디오스타","비열한거리","왕의남자","아일랜드","웰컴투동막골","헬보이","백투더퓨처"};
        final Integer[] imageID={R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,R.drawable.mov04,R.drawable.mov05,R.drawable.mov06,
                R.drawable.mov07,R.drawable.mov08,R.drawable.mov09,R.drawable.mov10};

        ArrayAdapter<String> adapter;
        adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,movie);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ImageView ivImage = findViewById(R.id.ivImage);
                ivImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                ivImage.setPadding(5,5,5,5);
                ivImage.setImageResource(imageID[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}

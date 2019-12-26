package com.example.user.spinnertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;



    ArrayList<ImageData> list = new ArrayList<ImageData>();
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner=findViewById(R.id.spinner);
        listInsertData();
        AdapterClass adapterClass = new AdapterClass(this,R.layout.image,list);
        spinner.setAdapter(adapterClass);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                imageView=findViewById(R.id.imageView);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setPadding(5,5,5,5);
                imageView.setImageResource(list.get(i).getImageID());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void listInsertData() {
        Integer[] imageID={R.drawable.mov11,R.drawable.mov12,R.drawable.mov13,R.drawable.mov14,R.drawable.mov15,R.drawable.mov16,R.drawable.mov17,
                R.drawable.mov18,R.drawable.mov19,R.drawable.mov20};

        String[] imageName={"가","나","다","라","마","바","사","아","자","차"};

        for(int i=0 ;i<10;i++){

            list.add(new ImageData(imageID[i],imageName[i]));
        }



    }
}

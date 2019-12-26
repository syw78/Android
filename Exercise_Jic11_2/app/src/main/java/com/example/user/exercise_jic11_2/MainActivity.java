package com.example.user.exercise_jic11_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Gallery;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Gallery gallery;
    static ImageView imageView;
    ArrayList<ImageData> list= new ArrayList<ImageData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("갤러리 영화 포스터");

        gallery=findViewById(R.id.gallery);
        imageView=findViewById(R.id.imageView);
        listInsertData();
        GalleryAdapter galleryAdapter=new GalleryAdapter(this,R.layout.gallery_image,list);
        gallery.setAdapter(galleryAdapter);

    }

    private void listInsertData() {
        Integer[] imageID={R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,R.drawable.mov04,R.drawable.mov05,R.drawable.mov06,R.drawable.mov07,
                R.drawable.mov08,R.drawable.mov09,R.drawable.mov10,};
        String[] imageName={"써니","완득이","괴물","라디오스타","비열한거리","왕의남자","아일랜드","웰컴투동막골","헬보이","백투더퓨처"};

        for(int i=0;i<9;i++){
            list.add(new ImageData(imageID[i],imageName[i]));
        }

    }
}

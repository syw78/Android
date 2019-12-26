package com.example.user.exercise_jic11_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<ImageData> list= new ArrayList<ImageData>();
    GridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("영화 포스터");
        gridView=findViewById(R.id.gridView);
        listInsertData();
        gridAdapter=new GridAdapter(this,R.layout.imge_layout,list);
        gridView.setAdapter(gridAdapter);
    }

    private void listInsertData() {
        Integer[] imageID={R.drawable.mov17,R.drawable.mov24,R.drawable.mov26,R.drawable.mov27,R.drawable.mov31,R.drawable.mov32,R.drawable.mov44,
                R.drawable.mov45,R.drawable.mov46,R.drawable.mov47,R.drawable.mov48,R.drawable.mov50,R.drawable.mov51,R.drawable.mov57,
                R.drawable.mov58};
        String[] imageName={"내이름은칸","아바타","국가대표","토이스토리","킹콩","반지의제왕","글래디에이터","쇼생크탈출","인생은아름다워","피아니스트",
                "더 사운드 오브 뮤직","나비효과","레옹","터미네이터","테이큰"};

        for(int i=0;i<30;i++){
            int num=(int)(Math.random()*(15));
            list.add(new ImageData(imageID[num],imageName[num]));
        }
    }
}

package com.example.user.mylistviewcreatertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<MyData> list = new ArrayList<MyData>();
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listView);
        myDataInsert();
        adapter=new MyAdapter(this,R.layout.mydata,list);
        listView.setAdapter(adapter);
    }

    private void myDataInsert() {
        int[] iv1={R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,R.drawable.mov04,R.drawable.mov05,R.drawable.mov11,R.drawable.mov12,R.drawable.mov13,R.drawable.mov14,R.drawable.mov15};
        int[] iv2={R.drawable.mov06,R.drawable.mov07,R.drawable.mov08,R.drawable.mov09,R.drawable.mov10,R.drawable.mov16,R.drawable.mov17,R.drawable.mov18,R.drawable.mov19,R.drawable.mov20,};
        String[] tv1={"써니","완득이","괴물","라디오스타","비열한거리","여인의 향기","쥬라기공원","포레스트캠프","자명종시계","혹성탈출"};
        String[] tv2={"왕의남자","아일랜드","웰컴투동막골","헬보이","백투더퓨처","아름다운 바다","내이름은 칸","해리포터","마더","킹콩을들다"};

        for(int i =0 ;i<iv1.length;i++){
            list.add(new MyData(iv1[i],iv2[i],tv1[i],tv2[i]));
        }

    }
}

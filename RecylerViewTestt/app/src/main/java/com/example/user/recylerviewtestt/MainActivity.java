package com.example.user.recylerviewtestt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private ArrayList<MainData> list =new ArrayList<MainData>();
    private RecyclerView recyclerView;
    private Button btnAdd;
    //레이아웃 매니저가 필요하다.
    private LinearLayoutManager linearLayoutManager;
    private MainAdapter mainAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        btnAdd=findViewById(R.id.btnAdd);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        mainAdapter=new MainAdapter(R.layout.activity_main,list);
        recyclerView.setAdapter(mainAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               MainData mainData = new MainData(R.mipmap.ic_launcher,"홍길동","긍정적인자");
                list.add(mainData);
                mainAdapter.notifyDataSetChanged(); //값이 바꼈으니까 바꿔


            }
        });

    }
}

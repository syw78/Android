package com.example.user.customlistviewstringtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<MyCustomDAO> list =new ArrayList<MyCustomDAO>();
    MyCustomAdapter adapter;
    //일반클래스에서 스스로가 인플렉션을 진행할려면 요청을 해야한다.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listView);
        listInsertData();
       adapter =new MyCustomAdapter(getApplicationContext(),R.layout.list_item_data,list);
       listView.setAdapter(adapter);
    }

    private void listInsertData() {
        for(int i=0;i<30;i++){
            final int index = i;
            list.add(new MyCustomDAO("다큘라"+index));
        }


    }
}

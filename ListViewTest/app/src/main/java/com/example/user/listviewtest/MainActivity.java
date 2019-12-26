package com.example.user.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<MyData> list = new ArrayList<MyData>();
   MyAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);
        listSetData();
        adapter=new MyAdapter(this,R.layout.list_data,list);
        listView.setAdapter(adapter);

    }

    private void listSetData() {
        String[] textView1={"가수","수박","박수","수리","리어카"};
        String[] textView2={"카센타","타이어","어류","류진","진라면"};
        int[] ivImage1={R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,R.drawable.pic5};
        int[] ivImage2={R.drawable.pic7,R.drawable.pic8,R.drawable.pic9,R.drawable.pici_icon,R.drawable.pic5};

        for(int i=0;i<textView1.length;i++){
            list.add(new MyData(ivImage1[i],ivImage2[i],textView1[i],textView2[i]));
        }


    }
}

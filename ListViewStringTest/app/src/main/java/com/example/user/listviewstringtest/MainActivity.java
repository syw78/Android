package com.example.user.listviewstringtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener, AdapterView.OnItemLongClickListener {
    ListView listView;
    Button btnAdd;
    EditText edtData;
    ArrayList<String> arrayData= new ArrayList<String>();
    ArrayAdapter<String> adapter; //뷰와 데이터를 연결해주는역할
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);
        btnAdd=findViewById(R.id.btnAdd);
        edtData=findViewById(R.id.edtData);

        //4번이 자료제공 데이터 완성이 되었음.
        arrayDataInput();
        //5번 정의되어 있는 어댑터를 사용한다.(방식설정)      화면 설계하는 부분          //이 부분이 2번
        adapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,arrayData);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
        //이벤트 처리
        listView.setOnItemClickListener(this);
        btnAdd.setOnClickListener(this);
        listView.setOnItemLongClickListener(this);

    }

    private void arrayDataInput() {
        String[] mid = {"data0","data1","data2","data3","data4","data5","data6","data7","data8","data9","data10","data11","data12","data13","data14","data15"};
        for(String data:mid){
            arrayData.add(data);

        }
        return;

    }

    @Override                                                      //위치 여분
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView textView=view.findViewById(android.R.id.text1);
        Log.d("MainActivity","i=+"+i+"l="+l+"데이터 ="+arrayData.get(i)+"진짜객체="+textView.getText().toString());



    }
    //추가할때
    @Override
    public void onClick(View view) {
        arrayData.add(edtData.getText().toString().trim());
        //노티파이 = 값이 변했을때 어댑터한테 다시 뿌려 라고 명령해주는 역할
        adapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(),"추가되었습니다",Toast.LENGTH_SHORT).show();
    }

    @Override                                                           //포지션 아이디
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        //get(i)는 무엇인가.
        String string = arrayData.get(i);
        arrayData.remove(i);
        adapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(),string+"삭제되었어요",Toast.LENGTH_SHORT).show();
        return false;
    }
}

package com.example.user.neweventacesstest;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnList,btnOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnList=findViewById(R.id.btnList);
        btnOption=findViewById(R.id.btnOption);

        btnList.setOnClickListener(this);
        btnOption.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnList:
                ArrayList<String> list =new ArrayList<String>();
                list.add("사과");
                list.add("딸기");
                list.add("수박");
                list.add("오렌지");
//                final String[] items = new String[list.size()];
//                int size=0;
//                for( String data :list ){
//                    items[size++]= data;
//                }
                final String[] items = list.toArray(new String[list.size()]);
                AlertDialog.Builder dialog =new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("목록상자");
                dialog.setItems(items,null);
                dialog.show();

                break;
            case R.id.btnOption:
                showDialogOption();
                break;


        }
    }

    private void showDialogOption() {
        ArrayList<String> list =new ArrayList<String>();
        list.add("사과");
        list.add("딸기");
        list.add("수박");
        list.add("오렌지");
//        final String[] items = new String[list.size()];
//        int size=0;
//        for( String data :list ){
//            items[size++]= data;
//        }
        final String[] items = list.toArray(new String[list.size()]);

        AlertDialog.Builder dialog =new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("목록상자");
        dialog.setSingleChoiceItems(items,-1,null);
        dialog.show();
    }
}

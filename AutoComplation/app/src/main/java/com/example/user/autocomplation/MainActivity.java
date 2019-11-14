package com.example.user.autocomplation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    MultiAutoCompleteTextView multiAutoCompleteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. 문자배열을 만든다.

        String[] data = {"CSI-뉴욕", "CSI-라스베가스", "CSI-마이애미",
                "Friends", "Fringe", "Lost"};
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        multiAutoCompleteTextView = findViewById(R.id.multiAutoCompleteTextView);

        //지금부터 어렵습니다 . 패턴을 인식해라.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, data);
        //데이터를 어댑터에 저장하는것.

        autoCompleteTextView.setAdapter(adapter); //어댑터와 뷰를 연결

        //동시에 여러개 선택할수 있도록 콤마 구분자를 연결함.
        MultiAutoCompleteTextView.CommaTokenizer token = new MultiAutoCompleteTextView.CommaTokenizer();
        multiAutoCompleteTextView.setTokenizer(token);
        multiAutoCompleteTextView.setAdapter(adapter);

    }
}

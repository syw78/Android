package com.example.user.exercise_jic8_1_diary;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker datePicker;
    Button btnPoint, btnPoint2, btnPoint3;
    EditText edtText;
    String fileName;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.datePicker);
        btnPoint = findViewById(R.id.btnPoint);
        edtText = findViewById(R.id.edtText);
        btnPoint2 = findViewById(R.id.btnPoint2);
        btnPoint3 = findViewById(R.id.btnPoint3);

        //현재날짜 가져오기
//        Calendar calender = Calendar.getInstance();
//        int year= calender.get(Calendar.YEAR);
//        int month = calender.get(Calendar.MONTH);
//        int day = calender.get(Calendar.DAY_OF_MONTH);
//        datePicker.init(year,month,day,null);
//        fileName=String.valueOf(year)+""+String.valueOf(month)+""+String.valueOf(day)+".txt";
        //======================================================================================

        initMemo();
        //첫화면에서 내용없을때
        if (edtText.getText().toString().trim().equals("")) {
            toastDisplay("저장된 메모 없음");
        }

        //날짜 선택할때
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                try {
                    fileName = String.valueOf(year) + "" + String.valueOf(month) + "" + String.valueOf(day) + ".txt";
                    FileInputStream fis = openFileInput(fileName); //읽어주는것 ,종이 ,빔프로젝트 같은것
                    // available은 사이즈 표현
                    byte[] readDiary = new byte[fis.available()]; //저장공간
                    fis.read(readDiary); //실제로 읽는것
                    edtText.setText(new String(readDiary));

                    btnPoint.setVisibility(View.VISIBLE);
                    btnPoint2.setVisibility(View.INVISIBLE);

                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    toastDisplay("저장된 메모가 없습니다.");
                    edtText.setText("");
                    btnPoint.setVisibility(View.INVISIBLE);
                    btnPoint2.setVisibility(View.VISIBLE);
                }
            }

        });
        //
        btnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.btnPoint:
                        reviseDiary();
                        break;
                }
            }

        });

        btnPoint2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btnPoint2:
                        saveDiary();
                        break;
                }
            }

        });
        btnPoint3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void initMemo() {
        Calendar calender = Calendar.getInstance();
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH);
        int day = calender.get(Calendar.DAY_OF_MONTH);
        //데이트피커에 초기값을 설정 이벤트는 걸지않음
        datePicker.init(year, month, day, null);
        //겹치지 않기 위해 파일명을 설정하는것.
        fileName = String.valueOf(year) + "" + String.valueOf(month) + "" + String.valueOf(day) + ".txt";

        try {
            //fileName=String.valueOf(year)+""+String.valueOf(month)+""+String.valueOf(day)+".txt";
            FileInputStream fis = openFileInput(fileName); //저장된위치에서 파일의 객체를 가져오는것.
            //fis.available은 그 파일의 사이즈를 표현하는것.
            byte[] readDiary = new byte[fis.available()];
            //1바이트 형식으로 읽어온다.
            fis.read(readDiary);
            //new String을 하는 이유는 readDiary는 바이트 형식이기떄문에 스트링으로 넣어주기위함이다.
            edtText.setText(new String(readDiary));
            btnPoint.setVisibility(View.VISIBLE);
            btnPoint2.setVisibility(View.INVISIBLE);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
            toastDisplay("저장된 메모가 없습니다.");
            //edtText.setText("");
            btnPoint.setVisibility(View.INVISIBLE);
            btnPoint2.setVisibility(View.VISIBLE);
        }
    }

    //저장하는 함수
    public void saveDiary() {
        try {
            FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            String diaryData = edtText.getText().toString();
            if (diaryData.trim().length() >= 1) {
                fos.write(diaryData.getBytes()); //저장하는부분
                btnPoint.setVisibility(View.VISIBLE);
                btnPoint2.setVisibility(View.INVISIBLE);
                fos.close();
                toastDisplay(fileName + "저장완료");
            } else {
                toastDisplay("메모 내용이 없습니다.");
                btnPoint.setVisibility(View.INVISIBLE);
                btnPoint2.setVisibility(View.VISIBLE);

            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //수정하는 함수
    public void reviseDiary() {
        try {                                               //문맥모드를 프라이빗으로
            FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            String diaryData = edtText.getText().toString();
            if (diaryData.trim().length() >= 1) {
                //다이어리데이터에 있는것을 바이트로 저장하겠다.
                fos.write(diaryData.getBytes());
                btnPoint.setVisibility(View.VISIBLE);
                btnPoint2.setVisibility(View.INVISIBLE);
                fos.close();
                toastDisplay(fileName + "수정완료");
            } else {
                toastDisplay("메모 내용이 없습니다.");
                btnPoint.setVisibility(View.INVISIBLE);
                btnPoint2.setVisibility(View.VISIBLE);
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void toastDisplay(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}

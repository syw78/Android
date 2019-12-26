package com.example.user.exercise_jic12_2databasepractice;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtName, edtNumber;
    Button btnInit, btnInsert, btnUpdate, btnDelete, btnSelect, btnSort;
    EditText et1, et2;
    MyDBHelper myDBHelper;
    SQLiteDatabase sqLiteDatabase;
    String strNames;
    String strNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("가수 그룹 관리 DB");


        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);

        btnInit = findViewById(R.id.btnInit);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnSelect = findViewById(R.id.btnSelect);
        btnSort = findViewById(R.id.btnSort);
        myDBHelper = new MyDBHelper(this);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);

        btnInit.setOnClickListener(this); //초기화
        btnInsert.setOnClickListener(this); //입력
        btnUpdate.setOnClickListener(this); //수정
        btnDelete.setOnClickListener(this); //삭제
        btnSelect.setOnClickListener(this); //조회
        btnSort.setOnClickListener(this); //정렬

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInit:
                sqLiteDatabase = myDBHelper.getWritableDatabase();
                myDBHelper.onUpgrade(sqLiteDatabase, 1, 2);
                sqLiteDatabase.close();
                break;
            case R.id.btnInsert:
                sqLiteDatabase = myDBHelper.getWritableDatabase();
                String str = "INSERT INTO groupTBL VALUES('" + edtName.getText().toString().trim() + "'," + edtNumber.getText().toString().trim().
                        trim() + ");";
                sqLiteDatabase.execSQL(str);
                sqLiteDatabase.close();
                Toast.makeText(getApplicationContext(), edtName.getText().toString() + "입력됨", Toast.LENGTH_SHORT).show();
                btnSelect.callOnClick();
                break;
            case R.id.btnUpdate:
                sqLiteDatabase = myDBHelper.getWritableDatabase();
                if (edtName.getText().toString() != "") {
                    sqLiteDatabase.execSQL
                            ("UPDATE groupTBL SET gNumber= '" + edtNumber.getText() + "' WHERE gName ='" + edtName.getText().toString() + "';");
                }
                sqLiteDatabase.close();
                Toast.makeText(getApplicationContext(), "수정됨", Toast.LENGTH_SHORT).show();
                    btnSelect.callOnClick();

                break;
            case R.id.btnDelete:
                sqLiteDatabase = myDBHelper.getWritableDatabase();
                if (edtName.getText().toString() != "") {
                    sqLiteDatabase.execSQL
                            ("DELETE FROM groupTBL WHERE gName = '" + edtName.getText().toString() + "';");
                }
                sqLiteDatabase.close();
                Toast.makeText(getApplicationContext(), "삭제됨", Toast.LENGTH_SHORT).show();
                btnSelect.callOnClick();

                break;
            case R.id.btnSelect:
                sqLiteDatabase = myDBHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqLiteDatabase.rawQuery("SELECT * FROM groupTBL;", null);
                strNames = "그룹이름" + "\r\n" + "---------" + "\r\n";
                strNumbers = "인원" + "\r\n" + "---------" + "\r\n";

                while (cursor.moveToNext()) {
                    strNames += cursor.getString(0) + "\r\n";
                    strNumbers += cursor.getString(1) + "\r\n";
                }
                et1.setText(strNames);
                et2.setText(strNumbers);

                cursor.close();
                sqLiteDatabase.close();
                break;
            case R.id.btnSort:

                sqLiteDatabase = myDBHelper.getReadableDatabase();
                Cursor cursor2;
                cursor2 = sqLiteDatabase.rawQuery("SELECT * FROM groupTBL ORDER BY gName;", null);
                String strNames = "그룹이름" + "\r\n" + "---------" + "\r\n";
                String strNumbers = "인원" + "\r\n" + "---------" + "\r\n";

                while (cursor2.moveToNext()) {
                    strNames += cursor2.getString(0) + "\r\n";
                    strNumbers += cursor2.getString(1) + "\r\n";
                }
                et1.setText(strNames);
                et2.setText(strNumbers);

                cursor2.close();
                sqLiteDatabase.close();

                break;
        }


    }
}

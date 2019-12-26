package com.example.user.singerdatabasemanagementtest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MyDBHelper myDBHelper;
    private EditText edtName, edtNumber, edtNameResult, edtNumberResult;
    private Button btnInit, btnInsert, btnSelect;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("가수 그룹 관리 DB");

        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        edtNameResult = findViewById(R.id.edtNameResult);
        edtNumberResult = findViewById(R.id.edtNumberResult);

        btnInit = findViewById(R.id.btnInit);
        btnInsert = findViewById(R.id.btnInsert);
        btnSelect = findViewById(R.id.btnSelect);
        myDBHelper = new MyDBHelper(this);

        btnInit.setOnClickListener(this);
        btnInsert.setOnClickListener(this);
        btnSelect.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInit:
                sqLiteDatabase = myDBHelper.getWritableDatabase();
                myDBHelper.onUpgrade(sqLiteDatabase, 1, 2);
                //데이타베이스를 닫는다.
                sqLiteDatabase.close();
                break;
            case R.id.btnInsert:
                sqLiteDatabase = myDBHelper.getWritableDatabase();
                String str = "INSERT INTO groupTBL values('" +
                        edtName.getText().toString().trim() + "' , " +
                        edtNumber.getText().toString().trim() + ");";
                sqLiteDatabase.execSQL(str);
                sqLiteDatabase.close();
                Toast.makeText(getApplicationContext(), edtName.getText().toString() + "입력됨", Toast.LENGTH_SHORT).show();
                Log.d("MainActivity", edtName.getText().toString() + "입력됨");
                break;
            case R.id.btnSelect:

                sqLiteDatabase = myDBHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqLiteDatabase.rawQuery("SELECT * FROM groupTBL;", null);

                String strNames = "그룹이름" + "\r\n" + "--------" + "\r\n";
                String strNumbers = "인원" + "\r\n" + "--------" + "\r\n";

                while (cursor.moveToNext()) {
                    strNames += cursor.getString(0) + "\r\n";
                    strNumbers += cursor.getString(1) + "\r\n";
                }

                edtNameResult.setText(strNames);
                edtNumberResult.setText(strNumbers);

                cursor.close();
                sqLiteDatabase.close();
                break;
        }
    }
}

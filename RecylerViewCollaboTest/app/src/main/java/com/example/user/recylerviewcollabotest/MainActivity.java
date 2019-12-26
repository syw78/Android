package com.example.user.recylerviewcollabotest;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtName, edtNumber;
    Button btnInit, btnInsert, btnUpdate, btnDelete, btnSelect, btnSort;
    static MyDBHelper myDBHelper;
    static SQLiteDatabase sqLiteDatabase;
    String strNames;
    String strNumbers;
    //static ArrayList<MyData> delete = new ArrayList<>();
    static ArrayList<MyData> list = new ArrayList<MyData>();
    RecyclerView recycler;
    LinearLayoutManager linearLayoutManager;
    MyAdapter adapter;

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
        recycler=findViewById(R.id.recycler);

        linearLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);
        adapter= new MyAdapter(R.layout.recycler,list);
        recycler.setAdapter(adapter);


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
                btnSelect.callOnClick();
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
                list.removeAll(list);
                sqLiteDatabase = myDBHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqLiteDatabase.rawQuery("SELECT * FROM groupTBL;", null);



                while (cursor.moveToNext()) {
                    list.add(new MyData(cursor.getString(0),cursor.getString(1)));
                }


                cursor.close();
                sqLiteDatabase.close();
                adapter.notifyDataSetChanged();
                break;
            case R.id.btnSort:
                list.removeAll(list);
                sqLiteDatabase = myDBHelper.getReadableDatabase();
                Cursor cursor2;
                cursor2 = sqLiteDatabase.rawQuery("SELECT * FROM groupTBL ORDER BY gName asc;", null);


                while (cursor2.moveToNext()) {
                    list.add(new MyData(cursor2.getString(0),cursor2.getString(1)));
                }


                cursor2.close();
                sqLiteDatabase.close();
                adapter.notifyDataSetChanged();
                break;
        }


    }
}

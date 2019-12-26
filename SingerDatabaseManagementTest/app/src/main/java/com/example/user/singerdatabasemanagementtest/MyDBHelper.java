package com.example.user.singerdatabasemanagementtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "groupDB";
    private static final int VERSION = 1;


    //데이타베이스 생성함.
    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);

    }

    //테이블 생성함.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String str = "CREATE TABLE groupTBL (" + "gName CHAR(20) PRIMARY KEY," + "gNumber Integer);";
        sqLiteDatabase.execSQL(str);
    }


    //테이블을 삭제하고 다시 생성한다.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS groupTBL");
        onCreate(sqLiteDatabase);
    }
}

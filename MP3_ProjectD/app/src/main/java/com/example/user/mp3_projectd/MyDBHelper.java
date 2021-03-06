package com.example.user.mp3_projectd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="musicDB";
    private static final int VERSION=1;

    public MyDBHelper(Context context) {
        super(context,DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String str="CREATE TABLE groupTBL(" + "gName CHAR(20) PRIMARY KEY," + "gText CHAR(20));";
        sqLiteDatabase.execSQL(str);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS groupTBL");
        onCreate(sqLiteDatabase);

    }
}

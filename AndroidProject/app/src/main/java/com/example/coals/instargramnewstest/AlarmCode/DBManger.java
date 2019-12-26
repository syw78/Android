package com.example.coals.instargramnewstest.AlarmCode;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManger extends SQLiteOpenHelper {
    public static final String  DB_NAME     = "alarm.db";
    public static final int     DB_VERSION  = 1;



    public DBManger( Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE AlarmList( id INTEGER PRIMARY KEY AUTOINCREMENT," + "hour CHAR(20), min CHAR(20),sun INTEGER," +
                "mon INTEGER,tue INTEGER,wen INTEGER,thu INTEGER,fri INTEGER,sat INTEGER,onoff INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

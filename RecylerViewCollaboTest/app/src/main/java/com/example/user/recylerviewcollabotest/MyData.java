package com.example.user.recylerviewcollabotest;

import android.database.Cursor;

public class MyData {

    String tvName , tvNumber;


    public MyData(String tvName, String tvNumber) {
        this.tvName = tvName;
        this.tvNumber = tvNumber;
    }

    public String getTvName() {
        return tvName;
    }

    public void setTvName(String tvName) {
        this.tvName = tvName;
    }

    public String getTvNumber() {
        return tvNumber;
    }

    public void setTvNumber(String tvNumber) {
        this.tvNumber = tvNumber;
    }
}

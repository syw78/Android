package com.example.user.mycalendartest;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

//부분화면으로 생각 할것.                    2개줄것.
public class MonthItemView extends AppCompatTextView {
    private MonthItem item;


    public MonthItemView(Context context) {
        super(context);
    }

    public MonthItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        setBackgroundColor(Color.WHITE);
    }

    public MonthItem getItem() {
        return item;
    }

    public void setItem(MonthItem item) {
        this.item = item;

        int day = item.getDayValue();
        if(day !=0){
            setText(String.valueOf(day));
        }else{
            setText("");
        }
    }
}

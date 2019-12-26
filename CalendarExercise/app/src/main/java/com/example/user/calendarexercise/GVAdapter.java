package com.example.user.calendarexercise;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.jar.Attributes;

public class GVAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<SubData> array = new ArrayList<>();
    public SubData[] list;
    Calendar mCalendar;
    int firstDay;
    int mStartDay;
    int startDay;

    TextView tvTalk;
    TextView tvDay;


    int curYear;
    int curMonth;
    int lastDay;
    // public int seletedPosition= -1;
    LayoutInflater layoutInflater;

    public GVAdapter() { //아무것도

    }

    public GVAdapter(Context context) {
        this.context = context;
        init();
    }
              // 이걸 시킨 , 호출한 사람이 누구냐
    public GVAdapter(Context context, Attributes attributes) {
        this.context = context;
        this.layout = layout;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        init();
    }


    public GVAdapter(Context context, int layout) {
        this.context = context;
        this.layout = layout;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        init();
    }

    private void init() {
        list = new SubData[42];
        mCalendar = Calendar.getInstance();
        recalculate();
        resetDayNumbers();
    }

    private void recalculate() {
        //array.clear();
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);
        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
        firstDay = getFirstDay(dayOfWeek);
        mStartDay = mCalendar.getFirstDayOfWeek();
        curYear = mCalendar.get(Calendar.YEAR);
        curMonth = mCalendar.get(Calendar.MONTH);
        lastDay = getMonthLastDay(curYear, curMonth);
    }

    private int getMonthLastDay(int curYear, int curMonth) {
        switch (curMonth + 1) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                if ((curYear % 4 == 0) && (curYear % 100 != 0) || (curYear % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
        }
    }

    private void resetDayNumbers() {
        for (int i = 0; i < 42; i++) {
            int dayNumber = (i + 1) - firstDay;
            if (dayNumber < 1 || dayNumber > lastDay) {
                dayNumber = 0;
            }

            //SubData subData = new SubData(String.valueOf(dayNumber),"1");
            // array.add(subData);
            list[i] = new SubData(dayNumber);
        }
    }


    private int getFirstDay(int dayOfWeek) {
        int result = 0;
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                result = 0;
                break;
            case Calendar.MONDAY:
                result = 1;
                break;
            case Calendar.TUESDAY:
                result = 2;
                break;
            case Calendar.WEDNESDAY:
                result = 3;
                break;
            case Calendar.THURSDAY:
                result = 4;
                break;
            case Calendar.FRIDAY:
                result = 5;
                break;
            case Calendar.SATURDAY:
                result = 6;
                break;
        }
        return result;
    }


    @Override
    public int getCount() {
        return 42;
    }

    @Override
    public Object getItem(int i) {
        return list[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(layout, null);
        }
        tvDay = view.findViewById(R.id.tvDay);
        tvTalk = view.findViewById(R.id.tvTalk);
        ImageView ivImage = view.findViewById(R.id.ivImage);
        //LinearLayout linear = view.findViewById(R.id.linear);
//        SubData item = list[i];
//        Log.d("GVAdapter", item.getTvDay() + " " + item.getTvTalk());

        SubData subData = list[i];

        if (subData.getTvDay()==0) {
            ivImage.setVisibility(View.INVISIBLE);
            tvDay.setVisibility(View.INVISIBLE);
            tvTalk.setVisibility(View.INVISIBLE);
        } else {
            ivImage.setVisibility(View.VISIBLE);
            tvDay.setVisibility(View.VISIBLE);
            tvTalk.setVisibility(View.VISIBLE);
            tvDay.setText(String.valueOf(subData.getTvDay()));
        }

        tvTalk.setText(subData.getTvTalk());

        return view;
    }

    private int getFirstDayOfWeek() {
        startDay = Calendar.getInstance().getFirstDayOfWeek();
        switch (startDay) {
            case Calendar.SATURDAY:
                return Time.SATURDAY;
            case Calendar.MONDAY:
                return Time.MONDAY;
            case Calendar.SUNDAY:
                return Time.SUNDAY;
        }
        return 0;
    }

    public void setPreviousMonth() {
        //현재가 11월이라면 10월달로 간다~
        mCalendar.add(Calendar.MONTH, -1); //현재달의 달력을 주는데 -1해서 저번달로간다.
        recalculate();
        resetDayNumbers();//객체를 다 만든다 42개
        //무슨 의미일까 ??
        //seletedPosition= -1;

    }

    public void setNextMonth() {
        //현재가 11월이라면 12월달로 간다~
        mCalendar.add(Calendar.MONTH, +1); //현재달의 달력을 주는데 +1해서 다음달로간다.
        recalculate();
        resetDayNumbers();//객체를 다 만든다 42개
        //무슨 의미일까 ??
        //  seletedPosition= -1;

    }
}

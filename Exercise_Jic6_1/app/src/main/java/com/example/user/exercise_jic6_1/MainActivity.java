package com.example.user.exercise_jic6_1;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    Chronometer chronometer;
    RadioButton rdoCalendar,rdoTimepicker;
    DatePicker datePicker;
    TimePicker timePicker;
    TextView tvYear,tvMonth,tvDay,tvHour,tvMinute;
    RadioGroup rboGroup;
    int year,month,dayofMonth,hour,miniute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("시간대예약앱");


        chronometer= findViewById(R.id.chronometer);
        rboGroup=findViewById(R.id.rboGroup);
        rdoCalendar= findViewById(R.id.rdoCalendar);
        rdoTimepicker= findViewById(R.id.rdoTime);
        datePicker= findViewById(R.id.datePicker);
        timePicker= findViewById(R.id.timePicker);
        tvYear= findViewById(R.id.tvYear);
        tvMonth= findViewById(R.id.tvMonth);
        tvDay= findViewById(R.id.tvDay);
        tvHour= findViewById(R.id.tvHour);
        tvMinute= findViewById(R.id.tvMinute);

        datePicker.setVisibility(View.INVISIBLE);
        timePicker.setVisibility(View.INVISIBLE);


        chronometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rboGroup.setVisibility(View.VISIBLE);
                chronometer.setBase(SystemClock.elapsedRealtime()); //시계 초기화 시켜줌
                chronometer.start();
            }
        });


        //1. 능
        rdoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.setVisibility(View.VISIBLE);
                timePicker.setVisibility(View.INVISIBLE);
            }
        });

        //2. 라디오버튼타임
        rdoTimepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePicker.setVisibility(View.VISIBLE);
                datePicker.setVisibility(View.INVISIBLE);
            }
        });


        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int h, int m) {
                hour = h; miniute=m;
            }
        });
        tvYear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                rboGroup.setVisibility(View.INVISIBLE);
                datePicker.setVisibility(View.INVISIBLE);
                timePicker.setVisibility(View.INVISIBLE);
                year=datePicker.getYear();
                month=datePicker.getMonth();
                dayofMonth=datePicker.getDayOfMonth();
                tvYear.setText(String.valueOf(year)+"년");
                tvMonth.setText(String.valueOf(month)+"월");
                tvDay.setText(String.valueOf(dayofMonth)+"일");
                tvHour.setText(String.valueOf(hour)+"시");
                tvMinute.setText(String.valueOf(miniute)+"분 예약됨");

                chronometer.stop();
                return false;
            }
        });

    }
}
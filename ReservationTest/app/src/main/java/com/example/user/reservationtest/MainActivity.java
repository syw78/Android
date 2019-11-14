package com.example.user.reservationtest;


import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    Chronometer chronometer;
    Button btnStart,btnEnd;
    RadioButton rdoCalendar,rdoTimepicker;
    CalendarView calendarView;
    TimePicker timePicker;
    TextView tvYear,tvMonth,tvDay,tvHour,tvMinute,tvMessage;
    int year,month,dayofMonth,hour,miniute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("시간대예약앱");


        chronometer= findViewById(R.id.chronometer);
        btnStart= findViewById(R.id.btnStart);
        btnEnd= findViewById(R.id.btnEnd);
        rdoCalendar= findViewById(R.id.rdoCalendar);
        rdoTimepicker= findViewById(R.id.rdoTime);
        calendarView= findViewById(R.id.calendarView);
        timePicker= findViewById(R.id.timePicker);
        tvYear= findViewById(R.id.tvYear);
        tvMonth= findViewById(R.id.tvMonth);
        tvDay= findViewById(R.id.tvDay);
        tvHour= findViewById(R.id.tvHour);
        tvMinute= findViewById(R.id.tvMinute);
        tvMessage= findViewById(R.id.tvMessage);

        //1. 라디오버튼 칼린더뷰를 보이기기능
        rdoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarView.setVisibility(View.VISIBLE);
                timePicker.setVisibility(View.INVISIBLE);
            }
        });

        //2. 라디오버튼타임
        rdoTimepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePicker.setVisibility(View.VISIBLE);
                calendarView.setVisibility(View.INVISIBLE);
            }
        });

        //3.예약시작을누르면 크로노미터가 작동되게한다.
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.setBase(SystemClock.elapsedRealtime()); // 크로노미터를초기화하는방법
                chronometer.start();
                chronometer.setTextColor(Color.RED);
            }
        });

        //4.칼렌더뷰를 선택 햇을때 현재년도, 날짜 , 월 , 일 값을
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView,
                                            int y, int m, int d) {
                year = y; month = m; dayofMonth=d;
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int h, int m) {
                hour = h; miniute=m;
            }
        });

        //5.예약완료를 했을때 처리하는 기능
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1.크로노미터
                chronometer.stop();
                tvYear.setText(String.valueOf(year)+"년");
                tvMonth.setText(String.valueOf(month)+"월");
                tvDay.setText(String.valueOf(dayofMonth)+"일");
                tvHour.setText(String.valueOf(hour)+"시");
                tvMinute.setText(String.valueOf(miniute)+"분");
                tvMessage.setText("예약완료");
            }
        });
    }
}
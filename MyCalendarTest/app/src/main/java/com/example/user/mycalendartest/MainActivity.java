package com.example.user.mycalendartest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnPrevious, btnNext;
    private GridView gvCalender;
    private TextView tvYearMonth;
    private MonthAdapter monthAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        gvCalender = findViewById(R.id.gvCalender);
        tvYearMonth = findViewById(R.id.tvYearMonth);

        //어댑터를 만든다!
        monthAdapter = new MonthAdapter(this);
        gvCalender.setAdapter(monthAdapter);

        btnNext.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);

        setYearMonth();
    }

    private void setYearMonth() {
//        monthAdapter.setNextMonth();
//        monthAdapter.notifyDataSetChanged();
        String yearMonth = monthAdapter.curYear + "년" + (monthAdapter.curMonth+1) + "월";
        tvYearMonth.setText(yearMonth);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNext:
                monthAdapter.setNextMonth();
                monthAdapter.notifyDataSetChanged();
                setYearMonth();

                break;
            case R.id.btnPrevious:
                monthAdapter.setPreviousMonth();
                monthAdapter.notifyDataSetChanged();
                setYearMonth();
                break;
        }
        gvCalender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                MonthItem curItem = monthAdapter.items[position];
                int day = curItem.getDayValue();
                String message = monthAdapter.curYear + "년" + monthAdapter.curMonth + "월" + day + "일" + "고생했어요";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

            }
        });

    }
}

package com.example.user.calendarexercise;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Button btnBack, btnNext;
    private TextView tvYM;
    private ArrayList<SubData> list = new ArrayList<>();
    private GridView gvCalendar;
    private GVAdapter gvAdapter;
    TextView tvTalk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("달력");

        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
        tvYM = findViewById(R.id.tvYM);
        gvCalendar = findViewById(R.id.gvCalendar);
        tvTalk = findViewById(R.id.tvTalk);


        gvAdapter = new GVAdapter(this, R.layout.sub);
        gvCalendar.setAdapter(gvAdapter);

        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        gvCalendar.setOnItemClickListener(this);

        setYearMonth();
        //gvAdapter.notifyDataSetChanged();


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNext:
                gvAdapter.setNextMonth();
                gvAdapter.notifyDataSetChanged();
                setYearMonth();

                break;
            case R.id.btnBack:
                gvAdapter.setPreviousMonth();
                gvAdapter.notifyDataSetChanged();
                setYearMonth();
                break;
        }

    }

    private void setYearMonth() {
//        monthAdapter.setNextMonth();
//        monthAdapter.notifyDataSetChanged();
        String yearMonth = gvAdapter.curYear + "년" + (gvAdapter.curMonth + 1) + "월";

        tvYM.setText(yearMonth);

    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

        final SubData item = gvAdapter.list[position];
        int day = item.getTvDay();
        if(day==0) return;

        View viewDialog = View.inflate(this,R.layout.dialog,null);
        final EditText edtText = viewDialog.findViewById(R.id.edtText);

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("메모 입력");
        dialog.setView(viewDialog);
        dialog.setIcon(R.mipmap.memo);
        dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("ff",position+"");
                Log.d("ff",gvAdapter.list[position].getTvDay()+"");
                gvAdapter.list[position].setTvTalk(edtText.getText().toString());
                Log.d("바뀐 값",gvAdapter.list[position].getTvTalk()+"");
                gvAdapter.notifyDataSetChanged();

            }
        });
        dialog.setNegativeButton("취소",null);
        dialog.show();


    }
}

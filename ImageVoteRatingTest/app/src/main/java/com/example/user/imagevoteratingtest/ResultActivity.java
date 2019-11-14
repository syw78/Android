package com.example.user.imagevoteratingtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv[]=new TextView[9];
    Integer tvID[]={R.id.tv01,R.id.tv02,R.id.tv03,R.id.tv04,R.id.tv05,R.id.tv06,R.id.tv07,R.id.tv08,R.id.tv09};
    RatingBar rBar[] = new RatingBar[9];
    Integer rBarID[]={R.id.rbar01,R.id.rbar02,R.id.rbar03,R.id.rbar04,R.id.rbar05,R.id.rbar06,R.id.rbar07,R.id.rbar08,R.id.rbar09};
    Button btnClose;
    int count[] = new int[9];
    String ivName[]=new String[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Intent intent = getIntent();
        count = intent.getIntArrayExtra("count");
        ivName = intent.getStringArrayExtra("ivName");
        btnClose=findViewById(R.id.btnClose);
        for(int i = 0; i < tvID.length ; i++ ){
            tv[i]=findViewById(tvID[i]);
            rBar[i]=findViewById(rBarID[i]);
            tv[i].setText(ivName[i]);
            rBar[i].setRating(count[i]);
        }
        btnClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.btnClose){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("message","다 끝났어요");
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}

package com.example.user.exercise_jic5_5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit11,edit22;
    Button btnP;
    Button btnM;
    Button btnG;
    Button btnN;
    TextView tv;
    String num1,num2;
    Integer result;

    Button[] numButtons = new Button[10];
    Integer[] numBtnID={R.id.Btn00,R.id.Btn11,R.id.Btn22,R.id.Btn33,R.id.Btn44,R.id.Btn55,R.id.Btn66,R.id.Btn77,
            R.id.Btn88,R.id.Btn99};
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("테이블레이아웃 계산기");
        edit11=findViewById(R.id.edit11);
        edit22=findViewById(R.id.edit22);

        btnP=findViewById(R.id.BtnPp);
        btnM=findViewById(R.id.BtnMm);
        btnG=findViewById(R.id.BtnGg);
        btnN=findViewById(R.id.BtnNn);
        tv=findViewById(R.id.tvv);

        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1= edit11.getText().toString();
                num2= edit22.getText().toString();
                result= Integer.parseInt(num1)+Integer.parseInt(num2);
                tv.setText("계산결과:"+result.toString());
            }
        });
        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1= edit11.getText().toString();
                num2= edit22.getText().toString();
                result= Integer.parseInt(num1)-Integer.parseInt(num2);
                tv.setText("계산결과:"+result.toString());
            }
        });
        btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1= edit11.getText().toString();
                num2= edit22.getText().toString();
                result= Integer.parseInt(num1)*Integer.parseInt(num2);
                tv.setText("계산결과:"+result.toString());
            }
        });
        btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1= edit11.getText().toString();
                num2= edit22.getText().toString();
                result= Integer.parseInt(num1)/Integer.parseInt(num2);
                tv.setText("계산결과:"+result.toString());
            }
        });
        for(i=0;i<numBtnID.length;i++){
            numButtons[i]=findViewById(numBtnID[i]);
        }
        for(i=0;i<numBtnID.length;i++){
            final int index;
            index=i;
            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(edit11.isFocused()==true){
                        num1=edit11.getText().toString()+numButtons[index].getText().toString();
                        edit11.setText(num1);
                    }else if(edit22.isFocused()==true){
                        num2=edit22.getText().toString()+numButtons[index].getText().toString();
                        edit22.setText(num2);
                    }else{
                        Toast.makeText(getApplicationContext(),"먼저 값을 입력하세요",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }
}

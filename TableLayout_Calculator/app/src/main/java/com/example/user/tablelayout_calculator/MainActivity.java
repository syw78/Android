package com.example.user.tablelayout_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit1,edit2;
    Button btnP;
    Button btnM;
    Button btnG;
    Button btnN;
    TextView tv;
    String num1,num2;
    Integer result;

    Button[] numButtons = new Button[10];
    Integer[] numBtnID={R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,
            R.id.btn8,R.id.btn9};
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("테이블레이아웃 계산기");
        edit1=findViewById(R.id.edit1);
        edit2=findViewById(R.id.edit2);

        btnP=findViewById(R.id.btnP);
        btnM=findViewById(R.id.btnM);
        btnG=findViewById(R.id.btnG);
        btnN=findViewById(R.id.btnN);
        tv=findViewById(R.id.tv);

        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1= edit1.getText().toString();
                num2= edit2.getText().toString();
                result= Integer.parseInt(num1)+Integer.parseInt(num2);
                tv.setText("계산결과:"+result.toString());
            }
        });
        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1= edit1.getText().toString();
                num2= edit2.getText().toString();
                result= Integer.parseInt(num1)-Integer.parseInt(num2);
                tv.setText("계산결과:"+result.toString());
            }
        });
        btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1= edit1.getText().toString();
                num2= edit2.getText().toString();
                result= Integer.parseInt(num1)*Integer.parseInt(num2);
                tv.setText("계산결과:"+result.toString());
            }
        });
        btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1= edit1.getText().toString();
                num2= edit2.getText().toString();
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
                    if(edit1.isFocused()==true){
                        num1=edit1.getText().toString()+numButtons[index].getText().toString();
                        edit1.setText(num1);
                    }else if(edit2.isFocused()==true){
                        num2=edit2.getText().toString()+numButtons[index].getText().toString();
                        edit2.setText(num2);
                    }else{
                        Toast.makeText(getApplicationContext(),"먼저 값을 입력하세요",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }
}

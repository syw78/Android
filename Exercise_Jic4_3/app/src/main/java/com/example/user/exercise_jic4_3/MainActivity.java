package com.example.user.exercise_jic4_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit1,edit2;
    Button btnAdd,btnSub,btnMul,btnDiv ,btnLe;
    TextView textRe;
    String num1,num2;
    Double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        setTitle("내가만든 계산기");
        edit1=findViewById(R.id.edit1);
        edit2=findViewById(R.id.edit2);

        btnAdd=findViewById(R.id.btnAdd);
        btnSub=findViewById(R.id.btnSub);
        btnMul=findViewById(R.id.btnMul);
        btnDiv=findViewById(R.id.btnDiv);
        btnLe=findViewById(R.id.btnLe);

        textRe=findViewById(R.id.textRe);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit1.getText().equals("")&&edit2.getText().equals("")){
                    Toast.makeText(getApplicationContext(),"값을입력해주세요",Toast.LENGTH_LONG).show();
                }

                num1=edit1.getText().toString();
                num2=edit2.getText().toString();
                result=Double.parseDouble(num1)+Double.parseDouble(num2);

                textRe.setText("계산결과:"+String.valueOf(result).toString());

            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit1.getText().equals("")&&edit2.getText().equals("")){
                    Toast.makeText(getApplicationContext(),"값을입력해주세요",Toast.LENGTH_LONG).show();
                }

                num1=edit1.getText().toString();
                num2=edit2.getText().toString();
                result=Double.parseDouble(num1)-Double.parseDouble(num2);

                textRe.setText("계산결과:"+String.valueOf(result).toString());

            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit1.getText().equals("")&&edit2.getText().equals("")){
                    Toast.makeText(getApplicationContext(),"값을입력해주세요",Toast.LENGTH_LONG).show();
                }

                num1=edit1.getText().toString();
                num2=edit2.getText().toString();
                result=Double.parseDouble(num1)*Double.parseDouble(num2);

                textRe.setText("계산결과:"+String.valueOf(result).toString());

            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit1.getText().equals("")&&edit2.getText().equals("")){
                    Toast.makeText(getApplicationContext(),"값을입력해주세요",Toast.LENGTH_LONG).show();
                }
                if(edit1.getText().equals("0")&&edit2.getText().equals("0")){
                    Toast.makeText(getApplicationContext(),"0으로나누지못해요",Toast.LENGTH_LONG).show();

                }

                num1=edit1.getText().toString();
                num2=edit2.getText().toString();
                result=Double.parseDouble(num1)/Double.parseDouble(num2);

                textRe.setText("계산결과:"+String.valueOf(result).toString());

            }
        });
        btnLe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit1.getText().equals("")&&edit2.getText().equals("")){
                    Toast.makeText(getApplicationContext(),"값을입력해주세요",Toast.LENGTH_LONG).show();
                }

                num1=edit1.getText().toString();
                num2=edit2.getText().toString();
                result=Double.parseDouble(num1)%Double.parseDouble(num2);

                textRe.setText("계산결과:"+String.valueOf(result).toString());

            }
        });



    }
}

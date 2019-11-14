package com.example.user.exercise_professor_test;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnSign,btnLogin;
    EditText edtName,edtAge,edtTall,edtID,edtPW;
    TextView tv1;
    View signUp,login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSign=findViewById(R.id.btnSign);
        btnLogin=findViewById(R.id.btnLogin);
        tv1=findViewById(R.id.tv1);

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp=View.inflate(MainActivity.this,R.layout.signup,null);
                final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("회원가입");
                dialog.setView(signUp);
                edtName=signUp.findViewById(R.id.edtName);
                edtAge=signUp.findViewById(R.id.edtAge);
                edtTall=signUp.findViewById(R.id.edtTall);
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tv1.setText(edtName.getText().toString().trim()+"\n"+edtAge.getText().toString().trim()+"\n"+edtTall.getText().toString().trim());
                    }
                });
                dialog.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"취소하셨습니다",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login=View.inflate(MainActivity.this,R.layout.login,null);
                final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("로그인");
                dialog.setView(login);
                edtID=login.findViewById(R.id.edtID);
                edtPW=login.findViewById(R.id.edtPW);

                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(edtID.getText().toString().equals("asd")&&edtPW.getText().toString().equals("123")){
                            tv1.setText("환영합니다 고객님");
                        }else{
                            tv1.setText("틀렸습니다 다시 입력하세요");
                        }
                    }
                });
                dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"로그인 취소",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });
    }
}

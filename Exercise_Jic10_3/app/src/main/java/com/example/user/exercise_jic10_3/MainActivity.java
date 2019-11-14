package com.example.user.exercise_jic10_3;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editText,editText2;
    RadioButton rboA,rboP,rboG,rboN;
    Button btnS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        rboA = findViewById(R.id.rboA);
        rboP = findViewById(R.id.rboP);
        rboG = findViewById(R.id.rboG);
        rboN = findViewById(R.id.rboN);
        btnS = findViewById(R.id.btnS);
        btnS.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(MainActivity.this,SubActivity.class);//편지봉투

        if(rboA.isChecked()){
                //편지봉투에 넣는것.
            intent.putExtra("num1",Integer.parseInt(editText.getText().toString()));
            intent.putExtra("num2",Integer.parseInt(editText2.getText().toString()));
            intent.putExtra("calc",new String("add"));

        }else if(rboP.isChecked()){
            intent.putExtra("num1",Integer.parseInt(editText.getText().toString()));
            intent.putExtra("num2",Integer.parseInt(editText2.getText().toString()));
            intent.putExtra("calc",new String("sub"));
        }else if(rboG.isChecked()){
            intent.putExtra("num1",Integer.parseInt(editText.getText().toString()));
            intent.putExtra("num2",Integer.parseInt(editText2.getText().toString()));
            intent.putExtra("calc",new String("mul"));
        }else if(rboN.isChecked()){
            intent.putExtra("num1",Integer.parseInt(editText.getText().toString()));
            intent.putExtra("num2",Integer.parseInt(editText2.getText().toString()));
            intent.putExtra("calc",new String("div"));
        }else{
            Toast.makeText(getApplicationContext(),"체크해주세요",Toast.LENGTH_SHORT).show();
            return;
        }

        startActivityForResult(intent,1000);//답장을 받는것. 송수신자간의 약속이필요하다 그약속인 1000번이다.
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000&&resultCode==1001){
           int result = data.getIntExtra("result",0);
           Toast.makeText(getApplicationContext(),"합계"+result,Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"오바야",Toast.LENGTH_SHORT).show();
        }
    }
}

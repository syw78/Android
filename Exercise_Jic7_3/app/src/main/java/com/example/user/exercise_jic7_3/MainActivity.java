package com.example.user.exercise_jic7_3;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtName,edtName2,edtEmail,edtEmail2;
    Button btnClick;
    View dialogView,toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);


        edtEmail=findViewById(R.id.edtEmail);
        btnClick = findViewById(R.id.btnClick);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dialogView=View.inflate(MainActivity.this,R.layout.user_information,null);
                final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("사용자정보");
                dialog.setIcon(R.mipmap.angel);
                dialog.setView(dialogView);
                edtEmail2 = dialogView.findViewById(R.id.edtEmail2);
                edtName2 = dialogView.findViewById(R.id.edtName2);
                edtName2.setText(edtName.getText().toString());
                edtEmail2.setText(edtEmail.getText().toString());
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if((!edtName2.getText().toString().equals(""))&&(!edtEmail2.getText().toString().equals(""))){
                            edtName.setText(edtName2.getText().toString());
                            edtEmail.setText(edtEmail2.getText().toString());
                        }else{
                            Toast.makeText(getApplicationContext(),"다시입력바람",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
                dialog.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        toastView=View.inflate(MainActivity.this,R.layout.toast_activity,null);
                        Toast toast = new Toast(MainActivity.this);
                        toast.setView(toastView);
                        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                        int x=(int)(Math.random()*display.getWidth());
                        int y= (int)(Math.random()*display.getHeight());
                        toast.setGravity(Gravity.TOP|Gravity.LEFT,x,y);
                        toast.show();
                    }
                });
                dialog.show();
            }
        });


    }
}

package com.example.user.exercise_talkbox_multichoice;


import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    Button btnChoice;
    String str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.tv1);
        btnChoice = findViewById(R.id.btnChoice);

        btnChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String[] list = new String[]{"누가", "오레오", "파이"};
                final boolean[] check = new boolean[]{false, false, false};
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("좋아하는 버전은 ?");

                dialog.setMultiChoiceItems(list, check, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                        check[i] = b;
                        for (int n = 0; n < check.length; n++) {
                            if (check[n]) {
                                str += list[n];
                            }
                            tv1.setText(str);
                        }
                        str = "";
                    }
                });

                dialog.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (check[0] == false && check[1] == false && check[2] == false) {
                            tv1.setText(null);
                        }
                    }
                });
                dialog.show();
            }
        });

    }
}

package com.example.user.framelayout_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Button[] button= new Button[3];
    int[] valueID={R.id.btnScreen1,R.id.btnScreen2,R.id.btnScreen3};
    LinearLayout layout1,layout2,layout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout1=findViewById(R.id.layout1);
        layout2=findViewById(R.id.layout2);
        layout3=findViewById(R.id.layout3);

        for(int i=0 ;i<valueID.length;i++){
            final int index= i;
            button[index]=findViewById(valueID[index]);
            button[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (index){
                        case 0:
                            layout1.setVisibility(View.VISIBLE);
                            layout2.setVisibility(View.INVISIBLE);
                            layout3.setVisibility(View.INVISIBLE);
                            break;
                        case 1:
                            layout1.setVisibility(View.INVISIBLE);
                            layout2.setVisibility(View.VISIBLE);
                            layout3.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            layout1.setVisibility(View.INVISIBLE);
                            layout2.setVisibility(View.INVISIBLE);
                            layout3.setVisibility(View.VISIBLE);
                            break;
                        default:

                            break;

                    }
                }
            });
        }
    }
}

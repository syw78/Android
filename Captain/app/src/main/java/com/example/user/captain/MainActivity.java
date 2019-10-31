package com.example.user.captain;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnHome;
    Button btnShow;
    RadioButton rdoPizza;
    RadioButton rdoBeer;
    EditText editText;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHome=findViewById(R.id.btnHome);
        btnShow=findViewById(R.id.btnShow);
        rdoBeer=findViewById(R.id.rdoBeer);
        rdoPizza=findViewById(R.id.rdoPizza);
        editText=findViewById(R.id.editText);
        imageView=findViewById(R.id.imageView);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://m.nate.com"));
                startActivity(intent);
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"눌럿음",Toast.LENGTH_LONG).show();
            }
        });

        rdoBeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setImageResource(R.drawable.beer2);
            }
        });
        rdoPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setImageResource(R.drawable.pizzz);
            }
        });
    }
}

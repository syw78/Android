package com.example.user.fourbutton;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //1. 변수선언 : 반드시 xml 화면의 위젯의 아이디와 일치시킬것.
    Button btnNate ,btnFri,btnGallary,btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //2.화면객체를 찾아서 가져온다.(형변환한다)
        btnNate=(Button)findViewById(R.id.btnNate);
        btnFri=(Button)findViewById(R.id.btnFri);
        btnGallary=(Button)findViewById(R.id.btnGallary);
        btnExit=(Button)findViewById(R.id.btnExit);

        //3. 이벤트를 건다.
        btnNate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //사이트 들어갈때
                //4.인텐트 기술 (내부적으로 잡혀있는 라이브러리를 불러오는 기술)
                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.nate.com")); //nate주소를 가져오는법
                startActivity(intent); //새로운화면을 보여줘라 그내용은 (  )안 라는뜻
            }
        });

        btnFri.setOnClickListener(new View.OnClickListener() { //전화를 걸때
            @Override
            public void onClick(View view) {
                //4.인텐트 기술 (내부적으로 잡혀있는 라이브러리를 불러오는 기술)
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/010-2971-4007")); //안드에서 tel이 폰번호라는뜻
                startActivity(intent);
            }
        });

        btnGallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //내화면을 보고싶을때
                //4.인텐트 기술 (내부적으로 잡혀있는 라이브러리를 불러오는 기술)
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));
                startActivity(intent);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() { //나갈때
            @Override
            public void onClick(View view) {
                //인텐트 기술
                finish(); //끝내기
            }
        });

        }
}

package com.example.user.fragmentview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener ,Fragment1Activity.OnFragmentInteractionListener {

    Button btnMenu1 , btnMenu2  , btnMenu3;

    // 12-02
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnMenu1=findViewById(R.id.btnMenu1);
        btnMenu2=findViewById(R.id.btnMenu2);
        btnMenu3=findViewById(R.id.btnMenu3);
        btnMenu1.setOnClickListener(this);
        btnMenu2.setOnClickListener(this);
        btnMenu3.setOnClickListener(this);

        btnMenu1.callOnClick();

    }

    @Override
    public void onClick(View view) {
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        beginTransaction () ==>을 참조하면 FragmentTransaction을 가져온다
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragmentActivity=null;

        switch (view.getId()){
            case R.id.btnMenu1 :
                fragmentActivity = new Fragment1Activity();
               // String name = bundle.getString("name");
                //Toast.makeText(getApplicationContext(),"컬러 1번 입니다",Toast.LENGTH_LONG).show();
                // Activity Main 에 있는 플레임 아웃을 대치 시키겠다는 뜻.
                // 대치 시켰으니 화면에 보여줘라.

                //===================================
                // 보내고자 하는 정보를 적는다 12-02
                //Bundle bundle = new Bundle(1);
               // bundle.putString("userID",name);
                // 번들을 전달한다
                fragmentActivity.setArguments(bundle);

                break; // commit 확정짓다.
                //===================================
            case R.id.btnMenu2 :
                fragmentActivity = new Fragment1Activity2();
                Toast.makeText(getApplicationContext(),"컬러 2번 입니다",Toast.LENGTH_LONG).show();
                break;
            case R.id.btnMenu3 :
                fragmentActivity = new Fragment1Activity3();
                Toast.makeText(getApplicationContext(),"컬러 3번 입니다",Toast.LENGTH_LONG).show();
                break;
        }
        ft.replace(R.id.frameLayout,fragmentActivity); // Activity Main 에 있는 플레임 아웃을 대치 시키겠다는 뜻.
        // 대치 시켰으니 화면에 보여줘라.
        ft.commit();// commit 확정!

    }

    // Fragment1에서 값이온다
    @Override
    public void onFragementInteraction(Bundle bundle) {

        this.bundle=bundle;
    }

    @Override
    public void onFragementInteraction(String name) {

    }
}

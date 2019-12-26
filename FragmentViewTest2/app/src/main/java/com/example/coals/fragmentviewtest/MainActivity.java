package com.example.coals.fragmentviewtest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener , Fragment2Activity.OnFragmentInteractionListener {
    Button btnMenu1,btnMenu2,btnMenu3,btnMenu4;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMenu1=findViewById(R.id.btnMenu1);
        btnMenu2=findViewById(R.id.btnMenu2);
        btnMenu3=findViewById(R.id.btnMenu3);
        btnMenu4=findViewById(R.id.btnMenu4);

        btnMenu1.setOnClickListener(this);
        btnMenu2.setOnClickListener(this);
        btnMenu3.setOnClickListener(this);
        btnMenu4.setOnClickListener(this);

        btnMenu1.callOnClick();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        Fragment fragmentActivity=null;
        switch (v.getId()){
            case R.id.btnMenu1 :
                fragmentActivity =new Fragment1Activity();
//                String name=bundle.getString("name");
//                bundle.putString("userID",name);
                fragmentActivity.setArguments(bundle);
                break;
            case R.id.btnMenu2 :
                fragmentActivity =new Fragment2Activity();
                break;
            case R.id.btnMenu3 :
                fragmentActivity =new Fragment3Activity();
                break;
            case R.id.btnMenu4 :
                fragmentActivity =new Fragment4Activity();
                break;
        }
        ft.replace(R.id.frameLayout,fragmentActivity);
        ft.commit();
    }

    @Override
    public void onFragementInteraction(Bundle bundle) {
        this.bundle=bundle;
    }
}

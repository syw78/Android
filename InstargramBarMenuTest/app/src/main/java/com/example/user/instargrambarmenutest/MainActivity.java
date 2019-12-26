package com.example.user.instargrambarmenutest;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomMenu;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;             //필요
    private FragmentTransaction fragmentTransaction;         //필요
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
    private Fragment5 fragment5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomMenu=findViewById(R.id.bottomMenu);
        frameLayout=findViewById(R.id.frameLayout);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        fragment5 = new Fragment5();

        //찾았으면 이제 이벤트처리를한다. 뭐냐면 바텀메뉴를 변경했을때 해당된 프래그먼트를 셋팅하는 리스너
        bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override //버튼을 누르면 여기서 받겠다. 바뀔때마다 기다리다가 바꾸면 콜백해줄게~ 이런말. 메뉴아이템이온다.
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case  R.id.action_1 : setChangeFragment(0); break;
                    case  R.id.action_2 : setChangeFragment(1); break;
                    case  R.id.action_3 : setChangeFragment(2); break;
                    case  R.id.action_4 : setChangeFragment(3); break;
                    case  R.id.action_five : setChangeFragment(4); break;

                }

                return true;
            }
        });
        setChangeFragment(0);




    }
    private void setChangeFragment(int position){
        //화면을 전환하기 위해서는 프래그먼트 매니저가 필요하다.
        fragmentManager = getSupportFragmentManager();
        //프래그먼트 매니저의 권한을 받아서 화면체인지하는 일을 하는 트랜젝션이 필요하다.
        fragmentTransaction = fragmentManager.beginTransaction(); //이걸로 화면 체인지한다.

        switch (position){
            //현재 프래그먼트 레이아웃에에 있는것을
            case 0 : fragmentTransaction.replace(R.id.frameLayout,fragment1);
            fragmentTransaction.commit();
            break;
            case 1 : fragmentTransaction.replace(R.id.frameLayout,fragment2);
                fragmentTransaction.commit();
                break;
            case 2 : fragmentTransaction.replace(R.id.frameLayout,fragment3);
                fragmentTransaction.commit();
                break;

            case 3: fragmentTransaction.replace(R.id.frameLayout,fragment4);
                fragmentTransaction.commit();
                break;

            case 4 : fragmentTransaction.replace(R.id.frameLayout,fragment5);
                fragmentTransaction.commit();
                break;


        }
    }
}

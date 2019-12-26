package com.example.user.viewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment1 extends Fragment {
    private TextView txtView;
    private View view;
    //뷰페이지를 통해서 슬라이딩이ㅏ 탭키를 눌러서 뷰페이저 액티비티가(프래그먼트) 변경이 되려면
    //현재 프래그먼트상태를 저장하는 변수가 필요하다.
    public static Fragment1 newInstance(){ //저장하는 변수
        Fragment1 fragment1 = new Fragment1(); //디폴트 생성자 생성

        return fragment1;
    }

    //액티비티에 oncreate와 같은 기능을 한다.
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragment1,container,false);
       txtView = view.findViewById(R.id.txtView);

        return view;
    }
}

package com.example.user.viewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment3 extends Fragment {
    private TextView txtView3;
    private View view;
    //뷰페이지를 통해서 슬라이딩이ㅏ 탭키를 눌러서 뷰페이저 액티비티가(프래그먼트) 변경이 되려면
    //현재 프래그먼트상태를 저장하는 변수가 필요하다.
    public static Fragment3 newInstance(){
        Fragment3 fragment3 = new Fragment3();

        return fragment3;
    }

    //액티비티에 oncreate와 같은 기능을 한다.
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragment3,container,false);
       txtView3 = view.findViewById(R.id.txtView3);

        return view;
    }
}

package com.example.user.fragmentview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Fragment1Activity extends Fragment implements View.OnClickListener {
    Button f1BtnName;
    // android.supprot . v4.app.Fragment Import 가 항상있어야함
    // 이 클래스는 inflater 를 상대한다.
    // inflater 는 XML로 정의된 View 를 실제 객체화 시키는 용도로 쓴다.
    View view;
    private final  static  String TAG="Fragment1Activity";
    public Fragment1Activity(){
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 1.위 메소드로 실행해야 한다. onCreate 하면서 R.layout.activiMain 을 가져옴 . 리턴값 void 이것이 inflater 이다
        // 해당된 뷰를 인플레이터  하는 기능이 있다.
        // 객체는 View로 리턴한다
        view =inflater.inflate(R.layout.fragment1,container,false);
        f1BtnName=view.findViewById(R.id.f1BtnName);
        f1BtnName.setOnClickListener(this);

        //값을 받아온다 12-02
        String userID=getArguments().getString("userID");
        Log.d(TAG,"userID"+userID);
        // 12.02


        return view;
    }
    public interface OnFragmentInteractionListener{
        // Fragment1에서 값이온다
        void onFragementInteraction(Bundle bundle);

        // 추상화 메소드 생성
        void onFragementInteraction(String name);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.f1BtnName :
                toastDisPlay("성공했습니다!");break;
        }
    }
    private void toastDisPlay(String s) {
//        Toast.makeText(view.getContext(),s,Toast.LENGTH_SHORT).show();
        Toast.makeText(view.getContext(),s,Toast.LENGTH_LONG).show();
    }
}

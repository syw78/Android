package com.example.user.fragmentview;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Fragment1Activity2 extends Fragment {
    View view;
    public Fragment1Activity2(){

    }
    private OnFragmentInteractionListener mListener;// 멤버변수 생성 여기에다가 부모 주소를 주면
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment2, container, false);
        Button btnSend =view.findViewById(R.id.btnSend);
        final EditText edtName =view.findViewById(R.id.edtName);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("name",name);
                mListener.onFragementInteraction(bundle);
            }
        });

        return view;
    }

    // 컨텍스트를 전달해주는 역활 this 값을 Fragment 1로 전달가능함
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener ){ // context 속에는 onFragment 가 들어있으면
            mListener =(OnFragmentInteractionListener)context;
            // 위처럼 형변환을 하면 부모는 자식의 객체를 가지게 된다
        }else{
            throw new RuntimeException(context.toString()+"OnFragmentInteractionListener 반드시 구현하세요");

        }
    }

    // 12.02
    public interface OnFragmentInteractionListener{
        // 추상화 메소드 생성
        void onFragementInteraction(Bundle bundle);

    }
}

package com.example.coals.fragmentviewtest;

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
import android.widget.Toast;

public class Fragment2Activity extends Fragment {
    //멤버변수선언
    private OnFragmentInteractionListener mListener;
    private EditText edtName;
    View view;

    public Fragment2Activity() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + "OnFragmentInteractionListener 를 반드시 구현하세요");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2, container, false);
        Button btnSend = view.findViewById(R.id.btnSend);
        edtName = view.findViewById(R.id.edtName);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edtName.getText().toString();
                Bundle bundle=new Bundle();
                bundle.putString("name",name);
                mListener.onFragementInteraction(bundle);
            }
        });

        return view;
    }

    public interface OnFragmentInteractionListener {
        void onFragementInteraction(Bundle bundle);
    }

}
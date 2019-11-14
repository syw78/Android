package com.example.user.fregmentviewtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Fragment1Activity extends Fragment implements View.OnClickListener {
    Button f1BtnName;
    View view;

    public Fragment1Activity() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        //번들 데이터를 주고받을수있는것?? 버퍼장치 ?
        view = inflater.inflate(R.layout.fragment1, container, false);
        f1BtnName = view.findViewById(R.id.f1BtnName);

        f1BtnName.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.f1BtnName:
                toastDisplay("반가워요,,,");
                break;
        }

    }

    private void toastDisplay(String s) {
        Toast.makeText(view.getContext(), "s", Toast.LENGTH_SHORT).show();
    }
}

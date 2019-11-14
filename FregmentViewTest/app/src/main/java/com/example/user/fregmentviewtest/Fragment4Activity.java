package com.example.user.fregmentviewtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Fragment4Activity extends Fragment {
    View view;

    public Fragment4Activity() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        //번들 데이터를 주고받을수있는것?? 버퍼장치 ?
        view = inflater.inflate(R.layout.fragment4, container, false);
        return view;

    }

    private void toastDisplay(String s) {
        Toast.makeText(view.getContext(), "s", Toast.LENGTH_SHORT).show();
    }
}

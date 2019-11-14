package com.example.user.myfragmentviewtest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragement4Activity extends Fragment implements View.OnClickListener {
    Button f4BtnSite;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment4,container,false);
        f4BtnSite =view.findViewById(R.id.f4BtnSite);

        f4BtnSite.setOnClickListener(this);

        return view;
    }
    public Fragement4Activity(){

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.f4BtnSite:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
                startActivity(intent);
                break;
        }
    }
}

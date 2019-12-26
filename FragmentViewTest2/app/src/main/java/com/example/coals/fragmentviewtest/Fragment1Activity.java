package com.example.coals.fragmentviewtest;

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
    private static final String TAG="Fragment1Activity";
    Button f1BtnName;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view= inflater.inflate(R.layout.fragment1,container,false);
        f1BtnName=view.findViewById(R.id.f1BtnName);
        f1BtnName.setOnClickListener(this);

        Bundle bundle =getArguments();
        if(bundle!=null){
            f1BtnName.setText(bundle.getString("name"));
            Log.d(TAG,"fragment1="+bundle.getString("name"));
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.f1BtnName:
                toastDisplay("5반에 오신것을 환영합니다.");
                break;


        }
    }

    private void toastDisplay(String s) {

        Toast.makeText(view.getContext(),s,Toast.LENGTH_LONG).show();
    }
}
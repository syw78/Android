package com.example.user.spinnertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterClass extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<ImageData> list;
    LayoutInflater layoutInflater;

    public AdapterClass(Context context, int layout, ArrayList<ImageData> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view=layoutInflater.inflate(layout,null);
        }
        TextView textView =view.findViewById(R.id.textView);
        ImageData imageData = list.get(i);
        textView.setText(list.get(i).getImageName());



        return view;
    }
}

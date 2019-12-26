package com.example.user.gridviewtest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class MyGridAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<MyData> list;
    LayoutInflater layoutInflater;

    public MyGridAdapter(Context context, int layout, ArrayList<MyData> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public MyGridAdapter(Context context, ArrayList<MyData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        if(view==null){
            view=layoutInflater.inflate(layout,null);
        }
        ImageView ivAdapter = view.findViewById(R.id.ivAdapter);
        MyData myData = list.get(position);
        ivAdapter.setImageResource(myData.getPosterID());
        ivAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View viewDialog = View.inflate(context,R.layout.dialog,null);
                ImageView imageView= viewDialog.findViewById(R.id.imageView);
                MyData myData1=list.get(position);
                imageView.setImageResource(myData1.getPosterID());
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("큰 포스터");
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setView(viewDialog);
                dialog.setPositiveButton("닫기",null);
                dialog.show();
            }
        });

        return view;
    }
}

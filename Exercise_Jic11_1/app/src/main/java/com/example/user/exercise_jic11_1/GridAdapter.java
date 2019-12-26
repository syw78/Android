package com.example.user.exercise_jic11_1;

import android.app.AlertDialog;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<ImageData> list;
    LayoutInflater layoutInflater;

    public GridAdapter(Context context, int layout, ArrayList<ImageData> list) {
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
        final ImageData imageData= list.get(position);
        ivAdapter.setImageResource(imageData.getImageID());
        ivAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View viewDialog = View.inflate(context,R.layout.dialog,null);
                ImageView imageView=viewDialog.findViewById(R.id.imageView);
                ImageData imageData1=list.get(position);
                imageView.setImageResource(imageData1.getImageID());
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle(imageData.getImageName());
                dialog.setView(viewDialog);
                dialog.setPositiveButton("닫기",null);
                dialog.show();
            }
        });


        return view;
    }
}

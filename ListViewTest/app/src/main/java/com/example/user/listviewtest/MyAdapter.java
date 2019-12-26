package com.example.user.listviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<MyData> list;
    LayoutInflater layoutInflater;

    public MyAdapter(Context context, int layout, ArrayList<MyData> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
           view = layoutInflater.inflate(layout,null);
        }
        ImageView ivImage1=view.findViewById(R.id.ivImage1);
        ImageView ivImage2=view.findViewById(R.id.ivImage2);
        TextView textView1=view.findViewById(R.id.textView1);
        TextView textView2=view.findViewById(R.id.textView2);

        MyData mydata=list.get(i);
        ivImage1.setImageResource(list.get(i).getIvImage1());
        ivImage2.setImageResource(list.get(i).getIvImage2());
        textView1.setText(list.get(i).getTextView1());
        textView2.setText(list.get(i).getTextView2());


        return view;
    }
}

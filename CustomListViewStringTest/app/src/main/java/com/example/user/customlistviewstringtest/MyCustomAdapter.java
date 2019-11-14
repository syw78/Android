package com.example.user.customlistviewstringtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter{
    //this를 저장할 멤버변수가 필요하다.
    Context context;
    int layout;
    ArrayList<MyCustomDAO> list;
    private LayoutInflater layoutInflater;

    public MyCustomAdapter(Context context, int layout, ArrayList<MyCustomDAO> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        //인플렉터 할 수 있도록 기능을 요청해서 받아온다.
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //데이타 자료의 갯수를 리턴해줘야 한다.
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
    //
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
      if(view==null){                   //viewGroup넣어줘도됨 널자리에
          view = layoutInflater.inflate(layout,null);
      }
      final TextView textView = view.findViewById(R.id.textView);
      MyCustomDAO myCustomDAO =list.get(position);
      textView.setText(myCustomDAO.getStringData());
      textView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Toast.makeText(context,textView.getText().toString(),Toast.LENGTH_SHORT).show();
          }
      });
        return view;
    }
}

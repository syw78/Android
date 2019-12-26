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
    Context context; //1번
    int layout; //2번
    ArrayList<MyCustomDAO> list; //4번
    private LayoutInflater layoutInflater;


    //생성자를 만든다.
    //일반클래스에서 스스로가 인플렉션을 진행하려면 요청을 해야한다.
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

    //해당하는 아이템 객체를 주는곳 Null로 표시하지 말고 ArrayList 항목을 던져줘도 된다.
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    //해당되는 아이템 위치를 가르킨다.
    @Override
    public long getItemId(int position) {
        return position;
    }
    //DataView를 제공받았으면 인플레이트 해야함 객체=findView 찾아야할 데이터를 넣는곳 (자체리턴하게 되면 listview 위치에 매치가 된다고함.)
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
      if(view==null){                   //viewGroup넣어줘도됨 널자리에
          view = layoutInflater.inflate(layout,null);
      }
      final TextView textView = view.findViewById(R.id.textView);
      MyCustomDAO myCustomDAO =list.get(position); //값을 가져와서 뿌려줘야함.
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

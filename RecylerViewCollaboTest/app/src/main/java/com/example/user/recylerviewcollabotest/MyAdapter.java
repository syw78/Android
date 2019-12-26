package com.example.user.recylerviewcollabotest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {
    int layout;
    ArrayList<MyData> list;

    public MyAdapter(int layout, ArrayList<MyData> list) {
        this.layout = layout;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout,viewGroup,false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyAdapter.CustomViewHolder customViewHolder, final int i) {
        customViewHolder.tvName.setText(list.get(i).getTvName());
        customViewHolder.tvNumber.setText(list.get(i).getTvNumber());
        customViewHolder.itemView.setTag(i);

        customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentName = customViewHolder.tvName.getText().toString();
                Toast.makeText(v.getContext(), currentName, Toast.LENGTH_SHORT).show();
            }
        });

        customViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MainActivity.sqLiteDatabase = MainActivity.myDBHelper.getWritableDatabase();
                if (list.get(i).getTvName().toString() != "") {
                    MainActivity.sqLiteDatabase.execSQL
                            ("DELETE FROM groupTBL WHERE gName = '" + list.get(i).getTvName() + "';");
                }
                MainActivity.sqLiteDatabase.close();
                list.remove(i);
                notifyDataSetChanged();
                return false;

            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0 ;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName,tvNumber;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
             tvName = itemView.findViewById(R.id.tvName);
             tvNumber = itemView.findViewById(R.id.tvNumber);

        }
    }
}

package com.example.neul7.recyclerviewtest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {
    // 1. context : 제공됨 (onCreateViewHolder -> ViewGroup)
    // 2. layout
    private int layout;
    // 3. ArrayList
    private ArrayList<MainData> list;

    //어댑터는 내가주는 정보들로 만든다. 레이아웃은 모양같은 느낌 그리고 그모양에 데이터를 넣을거야 그 데이터는 메인에있는 데이터를 넣었으면 좋겠어.
    public MainAdapter(int layout, ArrayList<MainData> list) {
        this.layout = layout;
        this.list = list;
    }

    // 1. getView inflater = viewHolder 에 있는 화면을 객체화 해서 해당된 viewHolder를 리턴한다.
    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        // 해당된 viewHolder 의 아이디를 찾는다.
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    // 3. getView 바인드
    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.CustomViewHolder customViewHolder, final int i) {
        customViewHolder.imgProfile.setImageResource(list.get(i).getImgProfile());
        customViewHolder.txtName.setText(list.get(i).getTxtName());
        customViewHolder.txtContent.setText(list.get(i).getTxtContent());
        customViewHolder.itemView.setTag(i);
        customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentName = customViewHolder.txtName.getText().toString();
                Toast.makeText(v.getContext(), currentName, Toast.LENGTH_SHORT).show();
            }
        });
        customViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String name = list.get(i).getTxtName();
                list.remove(i);
                notifyDataSetChanged();
                Toast.makeText(v.getContext(),name+"님 삭제완료",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    // 2. getView findViewById
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgProfile;
        public TextView txtName;
        public TextView txtContent;

        // itemView 에는 viewHolder 객체가 된 레이아웃 주소 전달됨
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.imgProfile);
            txtName = itemView.findViewById(R.id.txtName);
            txtContent = itemView.findViewById(R.id.txtContent);
        }
    }
}

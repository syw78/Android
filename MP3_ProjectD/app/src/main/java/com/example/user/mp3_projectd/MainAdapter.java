package com.example.user.mp3_projectd;

import android.content.Context;
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
    private int layout;
    private ArrayList<ItemData> list = new ArrayList<>();

    public MainAdapter(int layout, ArrayList<ItemData> list) {
        this.layout = layout;
        this.list = list;
    }

    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout,viewGroup,false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override //getView 바인드
    public void onBindViewHolder(@NonNull final MainAdapter.CustomViewHolder customViewHolder, final int position) {
        customViewHolder.txtTitle.setText(list.get(position).getTxtTitle());
        customViewHolder.txtSubTitle.setText(list.get(position).getTxtSubTitle());
        customViewHolder.txtDuration.setText(list.get(position).getTxtDuration());
        customViewHolder.imgAlbumart.setImageResource(list.get(position).getImgAlbumart());
        customViewHolder.itemView.setTag(position);
        customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentName = customViewHolder.txtTitle.getText().toString().trim();
                Toast.makeText(view.getContext(), currentName, Toast.LENGTH_SHORT).show();
            }
        });
        customViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return (list!=null)?list.size():0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
       public ImageView imgAlbumart;
       public TextView txtTitle,txtSubTitle,txtDuration;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAlbumart=itemView.findViewById(R.id.imgAlbumart);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtSubTitle=itemView.findViewById(R.id.txtSubTitle);
            txtDuration=itemView.findViewById(R.id.txtDuration);


        }
    }
}

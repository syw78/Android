package com.example.user.recylerviewtestt;

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
    //1. context : 리사이클러뷰는 제공이 된다.(onCreateView  Holder에서 -> ViewGroup 제공됨)
    private int layout;
    private ArrayList<MainData> list = new ArrayList<>();
   // private LayoutInflater layoutInflater; 우선 뺀다.

    public MainAdapter(int layout, ArrayList<MainData> list) {
        this.layout = layout;
        this.list = list;

    }



    //viewHolder에 있는 화면(layout)을 객체화 한다.
    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) { //겟뷰와 같음 여기서 인플래터 시킴
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(layout,viewGroup,false);//여기에 뷰를
        //해당된 viewholder 아이디를 찾는다.
        CustomViewHolder viewholder = new CustomViewHolder(view);
        return viewholder;//겟뷰에서 리턴값하고 같다. 객체화해서 해당된 viewHolder를 리턴한다.
    }
//imgProfile
    @Override                                                      //커스텀뷰홀더함수에 모든내용이 여기로온다
    public void onBindViewHolder(@NonNull final MainAdapter.CustomViewHolder customViewHolder, final int position) {//여기서는 매치시킨다 , 액션도 여기서
     //   customViewHolder.imgProfile.setImageResource(list.get(position).getImgLayout());
        customViewHolder.txtName.setText(list.get(position).getTxtNameOne());
        customViewHolder.txtContent.setText(list.get(position).getTxtNameTwo());
        //해도되고 안해도되고Re
        customViewHolder.itemView.setTag(position); //한칸

        customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentName = customViewHolder.txtName.getText().toString().trim();
                Toast.makeText(view.getContext(),currentName,Toast.LENGTH_SHORT).show();
            }
        });
        customViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String name = list.get(position).getTxtNameOne();
                list.remove(position);
                //제거가됬으니까 다시 보여줘라
                notifyItemChanged(position);
                Toast.makeText(view.getContext(),name+"s",Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() { //리스트 사이즈 줘버리면 됨
        return (list!=null)?list.size():0;
        //list.size() 주면 챙피스러운것.
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {// 알트엔터알트엔터알트엔터
        public ImageView imgProfile;
        public TextView txtName;
        public TextView txtContent;

        //itemView에는 뷰홀더가 객체가 된 레이아웃 주소가 전달된다.따라서 itemview 찾으면됨
        public CustomViewHolder(@NonNull View itemView) {//파인드뷰 아이디를 여기서 찾는다
            super(itemView);                //여기다 준다다
            imgProfile=itemView.findViewById(R.id.imgProfile);
            //imgProfile=itemView.findViewById(R.id.imgProfile);
            //imgProfile
            txtName=itemView.findViewById(R.id.txtName);
            txtContent=itemView.findViewById(R.id.txtContent);

        }

    }
}

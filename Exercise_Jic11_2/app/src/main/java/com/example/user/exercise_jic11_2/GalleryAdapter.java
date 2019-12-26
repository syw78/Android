package com.example.user.exercise_jic11_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.user.exercise_jic11_2.MainActivity.imageView;

public class GalleryAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<ImageData> list;
    LayoutInflater layoutInflater;

    public GalleryAdapter(Context context, int layout, ArrayList<ImageData> list) {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view=layoutInflater.inflate(layout,null);
        }
        ImageView ivAdapter = view.findViewById(R.id.ivAdapter);
        final ImageData imageData=list.get(i);
        ivAdapter.setImageResource(imageData.getImageID());

        ivAdapter.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
              View toastView=view.inflate(context,R.layout.toast,null);
                TextView textView=toastView.findViewById(R.id.textView);
                textView.setText(list.get(i).getImageName());
                Toast toast = new Toast(context);
                toast.setView(toastView);
                toast.show();

                imageView.setImageResource(list.get(i).getImageID());
                return false;
            }
        });

        return view;
    }
}

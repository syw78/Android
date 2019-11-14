package com.example.user.exercise_jic8_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MyPictureView extends View {

    private String src;

    public MyPictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (src != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(src);
            canvas.scale(2,2,0,0);
            canvas.drawBitmap(bitmap,0,0,null);
            bitmap.recycle();
        }
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}

package com.example.user.imagegihahakchange;


import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static int count = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    private static class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    count += 1;
                    count = (count > 4) ? (1) : (count);

            }
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Bitmap picture = BitmapFactory.decodeResource(getResources(),
                    R.drawable.renoir06);

            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;
            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;

            switch (count) {
                case 1:
                    canvas.rotate(45, cenX, cenY);
                    canvas.drawBitmap(picture, picX, picY, null);

                    break;
                case 2:
                    canvas.translate(-150, 200);
                    canvas.drawBitmap(picture, picX, picY, null);

                    break;
                case 3:
                    canvas.scale(2, 2, cenX, cenY);
                    canvas.drawBitmap(picture, picX, picY, null);


                    break;
                case 4:
                    canvas.skew(0.3f, 0.3f);
                    canvas.drawBitmap(picture, picX, picY, null);

                    break;
            }
            invalidate();
            picture.recycle();
        }
    }
}
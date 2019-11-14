package com.example.user.canvaslinecircletest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    final static int LINE = 1, CIRCLE = 2;
    public int curShape = LINE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    //옵션메뉴 그리기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "선그리기");
        menu.add(0, 2, 0, "원그리기");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                curShape = LINE;
                break;
            case 2:
                curShape = CIRCLE;
                break;

        }

        return true;
    }

    private class MyGraphicView extends View {
        public int startx = -1, starty = -1, stopx = -1, stopy = -1;


        public MyGraphicView(Context context) {
            super(context);
        }

        public MyGraphicView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        //선을 그릴지 원을그릴지 메뉴에서 선택정보 있어야됨(디폴트:선)
        //터치하는 좌표정보를 제공을 해야 그림을 그릴수 있다.

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startx = (int) event.getX();
                    starty = (int) event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    stopx = (int) event.getX();
                    stopy = (int) event.getY();
                    //무효화 영역처리  상당히 중요하다!
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:

                    break;
                case MotionEvent.ACTION_CANCEL:

                    break;

            }
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            //페인트 속성만 정의하면 된다.
            Paint paint = new Paint();
            paint.setAntiAlias(true); //부드럽게 해주는것
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setColor(Color.RED); //색상을 바꿀때는 LINE안으로 들가면 된다

//            switch (선택:라인,원){
//                case 라인: break;
//                case 원: break;
//            }
            switch (curShape) {
                case LINE:
                    //paint.setColor(Color.RED);
                    canvas.drawLine(startx, starty, stopx, stopy, paint);
                    break;

                case CIRCLE:
                    int radius = (int) Math.sqrt(Math.pow(stopx - startx, 2)
                            + Math.pow(stopy - starty, 2));

                    canvas.drawCircle(startx,starty,radius,paint);
                    break;

            }
        }
    }
}

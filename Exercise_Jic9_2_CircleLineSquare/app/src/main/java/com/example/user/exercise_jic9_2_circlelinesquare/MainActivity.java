package com.example.user.exercise_jic9_2_circlelinesquare;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    final static int LINE =1,CIRCLE=2,SQUARE=3,GREEN=4,RED=5,BLUE=6;
    public int curShape = LINE;
    static Paint paint = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"선그리기");
        menu.add(0,2,0,"원그리기");
        menu.add(0,3,0,"사각형그리기");
        //menu.add(0,4,0,"색상변경>>");
        SubMenu subMenu = menu.addSubMenu("색상변경");

        subMenu.add(0,4,0,"초록");
        subMenu.add(0,5,0,"빨강");
        subMenu.add(0,6,0,"파랑");

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                curShape=LINE;
                break;
            case 2:
                curShape=CIRCLE;
                break;
            case 3:
                curShape=SQUARE;
                break;
            case 4:
                paint.setColor(Color.GREEN);
                break;
            case 5:
                paint.setColor(Color.RED);
                break;
            case 6:
                paint.setColor(Color.BLUE);
                break;
        }

        return true;
    }

    private class MyGraphicView extends View {
        public int startx = -1 , starty= -1 , stopx= -1, stopy=-1;

        public MyGraphicView(Context context) {
            super(context);
        }

        public MyGraphicView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()){
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

            return  true;
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            //페인트 속성만 정의하면 된다.

            paint.setAntiAlias(true); //부드럽게 해주는것
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            //paint.setColor(Color.RED); //색상을 바꿀때는 LINE안으로 들가면 된다

            switch (curShape) {
                case LINE:
                    canvas.drawLine(startx, starty, stopx, stopy, paint);
                    break;
                case CIRCLE:
                    int radius = (int) Math.sqrt(Math.pow(stopx - startx, 2)
                            + Math.pow(stopy - starty, 2));
                    canvas.drawCircle(startx, starty, radius, paint);
                    break;
                case SQUARE:
                    canvas.drawRect(startx, starty, stopx, stopy, paint);
                    break;
            }
        }
    }
}

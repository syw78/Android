package com.example.user.miniphotoshoptest;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton ibZoomIn, ibZoomOut, ibRotate, ibBright, ibDark, ibGray;
    LinearLayout llBitMap;
    float scaleX = 1.0f, scaleY = 1.0f;
    float color = 1.0f;
    float satur = 1.0f;
    MyGraphicView myGraphicView;
    float angle = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("미니 포토샵");
        ibZoomIn = findViewById(R.id.ibZoomIn);
        ibZoomOut = findViewById(R.id.ibZoomOut);
        ibRotate = findViewById(R.id.ibRotate);
        ibBright = findViewById(R.id.ibBright);
        ibDark = findViewById(R.id.ibDark);
        ibGray = findViewById(R.id.ibGray);
        llBitMap = findViewById(R.id.llBitMap);

        myGraphicView = new MyGraphicView(this);
        //main Activity linearLayout bitmap에 view 로 저장한다.
        llBitMap.addView(myGraphicView);

        //이벤트처리
        ibZoomIn.setOnClickListener(this);
        ibZoomOut.setOnClickListener(this);
        ibRotate.setOnClickListener(this);
        ibBright.setOnClickListener(this);
        ibDark.setOnClickListener(this);
        ibGray.setOnClickListener(this);

        ibZoomIn.callOnClick();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibZoomIn:
                scaleX += 0.2f;
                scaleY += 0.2f;
                break;
            case R.id.ibZoomOut:
                scaleX -= 0.2f;
                scaleY -= 0.2f;
                break;
            case R.id.ibRotate:
                angle += 20;
                break;
            case R.id.ibBright:
                color += 0.2f;
                break;
            case R.id.ibDark:
                color -= 0.2f;
                break;
            case R.id.ibGray:
                satur = (satur == 0) ? (1) : (0);
                break;
        }
        //무효화영역 처리 요청
        myGraphicView.invalidate();
    }

    private class MyGraphicView extends View {
        public MyGraphicView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            //mygrahicview 중심점
            int centX = this.getWidth() / 2;
            int centY = this.getHeight() / 2;
            //스케일을 정한다.(ZoomIn,ZoomOut, 멤버변수참조
            canvas.scale(scaleX, scaleY, centX, centY);
            canvas.rotate(angle, centX, centY);
            //붓을 결정한다.
            Paint paint = new Paint();
            //bright,dark 기능 적용
            float[] array = {color, 0, 0, 0, 0, 0, color, 0, 0, 0, 0, 0, color, 0, 0, 0, 0, 0, 1, 0};
            ColorMatrix colorMatrix = new ColorMatrix(array);

            if (satur == 0.0f) {
                colorMatrix.setSaturation(0.0f);
            }
            paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            //이미지를 비트맵으로 가져옴
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lena256);
            //비트맵을 캔버스에 그리기위한 좌표 계산
            int x = (this.getWidth() - bitmap.getWidth()) / 2;
            int y = (this.getHeight() - bitmap.getHeight()) / 2;

            //캔버스에 비트맵을 그린다.
            canvas.drawBitmap(bitmap, x, y, paint);
            //비트맵을 메모리에 로딩(메모리 버퍼기능)
            bitmap.recycle();


        }
    }//end of mygrahicview


}
package com.example.user.exercise_jic10_2;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView[] iv = new ImageView[9];
    private Integer[] ivID = new Integer[]{R.id.iv01, R.id.iv02, R.id.iv03, R.id.iv04, R.id.iv05, R.id.iv06, R.id.iv07, R.id.iv08, R.id.iv09};
    private Button btnOpen;
    private String[] ivName = {"독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀", "이레느깡 단 베르망", "잠자는 소녀", "테라스의 두 자매", "피아노 레슨", "피아노 앞의 소녀들", "해변에서"};
    private int count[] = new int[9];
    final static int REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpen = findViewById(R.id.btnOpen);
        btnOpen.setOnClickListener(this);
        for (int i = 0; i < ivID.length; i++) {
            iv[i] = findViewById(ivID[i]);
            iv[i].setOnClickListener(this);
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //이미지1번을 선택했을때 처리하는 방법
            case R.id.iv01:
                ivChecked(0);
                break;
            case R.id.iv02:
                ivChecked(1);
                break;
            case R.id.iv03:
                ivChecked(2);
                break;
            case R.id.iv04:
                ivChecked(3);
                break;
            case R.id.iv05:
                ivChecked(4);
                break;
            case R.id.iv06:
                ivChecked(5);
                break;
            case R.id.iv07:
                ivChecked(6);
                break;
            case R.id.iv08:
                ivChecked(7);
                break;
            case R.id.iv09:
                ivChecked(8);
                break;
            case R.id.btnOpen:

                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("ivName", ivName);
                intent.putExtra("count", count);
                startActivityForResult(intent, REQUEST_CODE);

                break;
        }

    }//end of onClick


    private void ivChecked(int i) {
        count[i] = (count[i] == 5) ? 5 : ++count[i];
        if (count[i] == 5) {
            toastDisplay("최고점수 구만혀");
        } else {
            toastDisplay(ivName[i]+" "+count[i]+"번 점수를 줬어요");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent returnIntent) {
        super.onActivityResult(requestCode, resultCode, returnIntent);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String message = returnIntent.getStringExtra("message");
            toastDisplay("투표점수는 초기화됩니다.");
            for (int i = 0; i < count.length; i++) {
                count[i] = 0;
            }
        }
    }



    private void toastDisplay(String s) {
        Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT).show();
    }
}

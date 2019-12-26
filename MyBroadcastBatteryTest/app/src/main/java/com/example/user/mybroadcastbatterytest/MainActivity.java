package com.example.user.mybroadcastbatterytest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView ivBattery;
    private EditText edtBattery;
    private BroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivBattery=findViewById(R.id.ivBattery);
        edtBattery=findViewById(R.id.edtBattery);

        callBroadCastReceiver();

    }

    @Override
    protected void onResume() {
        super.onResume();
        intentFilter=new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);  //내가원하는 필터는 이렇게할때만 액션해주세요
        //등록을해야한다. 그게바로 레지스터
        registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver); //더이상 브로드캐스트 리시버를 받지 않겠다.
    }

    private void callBroadCastReceiver() {             //브로드캐스트 임시객체를 만들어서 받는것이다. 원래는 클래스를 만들어야하는데
        broadcastReceiver =new BroadcastReceiver(){
            //여기서 정보를 받음 -> 처리하면 됨.
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction(); //브로드캐스트에서 보내준 인텐트를 딱 받는다.
                //배터리 변화를 주었을때 받는 action
                if(action.equals(Intent.ACTION_BATTERY_CHANGED)){
                    int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
                    edtBattery.setText("현재 충전량"+remain+"\n");

                    if(remain >= 90){
                        ivBattery.setImageResource(R.drawable.battery_100);
                    }else if(remain >=70){
                        ivBattery.setImageResource(R.drawable.battery_80);
                    }else if(remain >=50){
                        ivBattery.setImageResource(R.drawable.battery_60);
                    }else if(remain >=10){
                        ivBattery.setImageResource(R.drawable.battery_20);
                    }else{
                        ivBattery.setImageResource(R.drawable.battery_0);
                    }

                    int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0);
                    switch (plug){
                        case 0:
                            edtBattery.append("전원 연결 : 안됨 ㅠ");
                            break;
                        case BatteryManager.BATTERY_PLUGGED_AC:
                            edtBattery.append("전원 연결 : 어댑터 연결~");
                            break;
                        case BatteryManager.BATTERY_PLUGGED_USB:
                            edtBattery.append("전원 연결 : USB 연결됨");
                            break;
                    }


                }

            }
        };

    }

    //브로드캐스트 리시버를  받고자하면 , 받고자하는 앱은 이것을 만들어야한다.BroadcastReceiver 객체!

}

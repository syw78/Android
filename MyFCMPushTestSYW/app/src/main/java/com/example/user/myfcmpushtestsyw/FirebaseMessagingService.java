package com.example.user.myfcmpushtestsyw;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {  //메세지받는것

    private static final String TAG="FirebaseMessagingService";
    private String title;
    private String message;

    @SuppressLint("LongLogTag")
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG,"onMessgaeReceived");
        title = remoteMessage.getNotification().getTitle();//받고싶지않아도 강제로 통보받는것
        message = remoteMessage.getNotification().getBody();//받고싶지않아도 강제로 통보받는것

        Intent intent = new Intent(this,MainActivity.class);
        //액티비티를 계속부르면 스택으로 쌓인다.
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //어떤시점에(사건이발생하면) 누가(그사건에있는) 누구를(메인엑티비티를?) 이것을 결정한다 펜딩인텐트가.
        //액티비티가 아니다 이것은 서비스이다 서비스는 눈에 안보인다! 앱을꺼도 음악이재생이되는것 그게 서비스 등록하는것이다.
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        //상태바
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(new long[]{1,2000});

        //너 통지메세지오면 이기능을 실행해줘야되 그게바로 어떤시점
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());


    }
}

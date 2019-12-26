package com.example.user.myservicemusictest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {
    private static final String TAG="MusicService";
    private MediaPlayer mediaPlayer;


    @Override

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG,"서비스 onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"서비스 onStartCommand()");
        mediaPlayer=MediaPlayer.create(this,R.raw.dean);
        mediaPlayer.setLooping(true); //몇번돌을래
        mediaPlayer.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        mediaPlayer.stop();
        Log.d(TAG,"서비스 onDestroy()");
        super.onDestroy();
    }

}

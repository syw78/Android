package com.example.user.myfcmpushtestsyw;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseInstanceIDService extends FirebaseInstanceIdService { //토큰받는것
    private static final String TAG = "MyFireBaseIIDService";

    // 토큰이여 토큰 이 토큰이없으면 파이어베이스한테 푸쉬를 못받는다. 토큰은 반드시 받아야한다.
    //이름은 반드시 저걸로 되어있어야한다.
    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG ,token);
        sendRegistrationToServer(token); //확장성을 위해서
    }

    //나중에서버에 정보를 보낼때 확장시킬때 사용함 지금은 사용하지않음
    private void sendRegistrationToServer(String token) {
    }
}

package com.example.user.volleywebhttptest;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


//반드시 스트링 리퀘스트를 받아야된다 룰! 데이터방식은 무조건 스트링.
public class RegisterRequest extends StringRequest {
    final static private String URL ="http://tjdusdn78.dothome.co.kr/Register.php";
    private Map<String,String> map;
    private String userID;
    private String userPassword;
    private String userName;
    private int userAge;


    //생성자 반드시 만들어야함.
    //요 생성자 상당히 중요 포스트 방식 유알엘 들었을때 에러났을때
    public RegisterRequest(String userID, String userPassword,String userName , int userAge, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        map=new HashMap<>();
        this.userID= userID;
        this.userPassword= userPassword;
        this.userName=userName;
        this.userAge=userAge;
    }


    //맵으로 주는건 약속. 값을 넣어주는 역할.  맵은 보이지않는다 액티비티에 registerRequest에 들어간다.
    @Override   //겟 파람스는 보이지않는 콜백이라고 생각해라!
    protected Map<String, String> getParams() throws AuthFailureError {
        map.put("userID",this.userID);
        map.put("userPassword",this.userPassword);
        map.put("userName",this.userName);
        map.put("userAge",String.valueOf(this.userAge));

        return map;
    }
}

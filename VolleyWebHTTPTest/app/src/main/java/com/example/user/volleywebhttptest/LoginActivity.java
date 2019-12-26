package com.example.user.volleywebhttptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtID,edtPassword;
    private Button btnLogin,btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtID=findViewById(R.id.edtID);
        edtPassword=findViewById(R.id.edtPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnRegister=findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        String userID=edtID.getText().toString().trim();
        String userPassword=edtPassword.getText().toString().trim();


        //아이디랑 비번이 일치한경우 로그인 xml로 다시 보내준다.
        Response.Listener<String> responseListener=new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    boolean success= jsonObject.getBoolean("success");
                    if(success){
                        String userID= jsonObject.getString("userID");
                        String userPassword= jsonObject.getString("userPassword");
                        String userName= jsonObject.getString("userName");

                        Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("userID",userID);
                        intent.putExtra("userPassword",userPassword);
                        startActivity(intent);
                        toastDisplay("로그인 성공했어요");

                    }else{
                        toastDisplay("로그인 실패했어요");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        //Response.Listener<String> errorResponseListener=null; 에러날경우
        LoginRequest loginRequest = new LoginRequest(userID,userPassword,responseListener);
        RequestQueue queue =Volley.newRequestQueue(LoginActivity.this);
        queue.add(loginRequest);
    }

    private void toastDisplay(String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
}

package com.example.user.volleywebhttptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtID,edtPassword, edtName, edtAge;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtID=findViewById(R.id.edtID);
        edtPassword=findViewById(R.id.edtPassword);
        btnRegister=findViewById(R.id.btnRegister);
        edtName=findViewById(R.id.edtName);
        edtAge=findViewById(R.id.edtAge);

        btnRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String userID=edtID.getText().toString().trim();
        String userPassword=edtPassword.getText().toString().trim();
        String userName=edtName.getText().toString().trim();
        String userAge=edtAge.getText().toString().trim();

        //답변이 오면 등록하는것. 룰!!
        Response.Listener<String> responseListener=new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //html오브젝트로 해석.
                    JSONObject jsonObject=new JSONObject(response);
                    boolean success= jsonObject.getBoolean("success");
                    if(success){

                        Intent intent =new Intent(RegisterActivity.this,MainActivity.class);
                        intent.putExtra("userID",edtID.getText().toString());
                        intent.putExtra("userPassword",edtPassword.getText().toString());
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
        //발리는 내가 보내는것 보낼때 겟이나 포스트방식으로 보내고 서버는 겟(URL)이나 포스트(URL을통해서 안가는것  포스트속에 스트링방식 (데이터를 맵으로 보낸다 그럼 발리)로 받는다.
        //서버가 제이슨방식으로 답변을해준다. 그럼 리스너를 통해서 제이슨을통해 받는다.

        //Response.Listener<String> errorResponseListener=null; 에러날경우 발리에 키는 3줄이다!
        RegisterRequest registerRequest = new RegisterRequest(userID,userPassword,userName,Integer.parseInt(userAge) ,responseListener);
        // 큐가 발리를 통해서 만들어짐. 큐는 뭐냐 데이터를 넣어서 보내는 접점장소. 수영장에서 줄서서 대기하고있는 라인이 큐
        RequestQueue queue =Volley.newRequestQueue(RegisterActivity.this); //발리는 보내는 방식.
        //괄호안에가 사람. 도착지 통과할 관문(겟 포스트) 그리고 전달할 데이터를 가지고있다.
        queue.add(registerRequest);


    }

    private void toastDisplay(String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
}

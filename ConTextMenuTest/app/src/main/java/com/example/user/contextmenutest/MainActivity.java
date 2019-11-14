package com.example.user.contextmenutest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    Button btnContextMenu,btnContextMenu2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout= findViewById(R.id.linearLayout);
        btnContextMenu = findViewById(R.id.btnContextMenu);
        btnContextMenu2= findViewById(R.id.btnContextMenu2);

        //1.컨텍스트메뉴는 해당 된 위젯을 선택을 해서 등록해야된다.
        registerForContextMenu(btnContextMenu);
        registerForContextMenu(btnContextMenu2);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater =getMenuInflater();

        if(v==btnContextMenu){
            menuInflater.inflate(R.menu.context_menu,menu);
        }else if(v==btnContextMenu2){
            menuInflater.inflate(R.menu.context_menu,menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.contextBlue:
                linearLayout.setBackgroundColor(Color.BLUE);
                break;
            case R.id.contextGreen:
                linearLayout.setBackgroundColor(Color.GREEN);
                break;
            case R.id.contextRed:
                linearLayout.setBackgroundColor(Color.RED);
                break;
        }

        return true;
    }
}

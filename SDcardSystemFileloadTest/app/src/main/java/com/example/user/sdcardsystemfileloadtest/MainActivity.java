package com.example.user.sdcardsystemfileloadtest;

import android.Manifest;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Button btnFileList;
    EditText edtFileList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFileList=findViewById(R.id.btnFileList);
        edtFileList=findViewById(R.id.edtFileList);

        ActivityCompat.requestPermissions(this,new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
        btnFileList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = Environment.getRootDirectory().getAbsolutePath();
                File[] dirFile = (new File(path)).listFiles();
                String strFileName="";
                for(int i=0;i<dirFile.length;i++){
                    if(dirFile[i].isDirectory()==true){
                        strFileName="<폴더>"+dirFile[i].toString();
                    }else{
                        strFileName="<파일>"+dirFile[i].toString();
                    }

                    edtFileList.setText(edtFileList.getText()+"\n"+strFileName);
                }
            }
        });
    }
}

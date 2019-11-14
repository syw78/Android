package com.example.user.exercise_jic7_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    EditText edtText;
    ImageView image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtText = findViewById(R.id.edtText);
        image1 = findViewById(R.id.image1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.change_images, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.gun:
                image1.setImageResource(R.drawable.gun);
                break;
            case R.id.tank:
                image1.setImageResource(R.drawable.tank);
                break;
            case R.id.ttop:
                image1.setImageResource(R.drawable.tt);
                break;
            case R.id.turn:
                int result = Integer.parseInt(edtText.getText().toString());
                image1.setRotation(result);
                break;
            default:
                break;


        }


        return true;
    }
}

package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, get_started.class);
                startActivity(i);
                finish();
            }
        }, 3500);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Application Starting", Toast.LENGTH_LONG).show();
    }

}

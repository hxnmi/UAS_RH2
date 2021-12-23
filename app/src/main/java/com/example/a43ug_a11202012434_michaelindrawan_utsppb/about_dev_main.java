package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class about_dev_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_dev_main);
    }
    public void profilFandy(View view){
        Intent i = new Intent(about_dev_main.this, about_dev_fandy.class);
        startActivity(i);
    }
    public void profilHanif(View view){
        Intent i = new Intent(about_dev_main.this, about_dev_hanif.class);
        startActivity(i);
    }
    public void profilMichael(View view){
        Intent i = new Intent(about_dev_main.this, about_dev_michael.class);
        startActivity(i);
    }
    public void profilTito(View view){
        Intent i = new Intent(about_dev_main.this, about_dev_tito.class);
        startActivity(i);
    }
}
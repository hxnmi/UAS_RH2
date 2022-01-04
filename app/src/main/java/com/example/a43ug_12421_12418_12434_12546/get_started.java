package com.example.a43ug_12421_12418_12434_12546;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class get_started extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
    }
    public void tapAnywhere(View view) {
        Intent i = new Intent(get_started.this, log_in.class);
        startActivity(i);
    }

}
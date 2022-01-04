package com.example.a43ug_12421_12418_12434_12546;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class successful_reseting_password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_reseting_password);
    }
    public void backToLogin(View view) {
        Intent i  = new Intent(successful_reseting_password.this, log_in.class);
        startActivity(i);
        finish();
    }
}
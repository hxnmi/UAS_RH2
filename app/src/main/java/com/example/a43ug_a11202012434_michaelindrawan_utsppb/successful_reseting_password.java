package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.security.PublicKey;

public class successful_reseting_password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_reseting_password);
    }
    public void backToLogin(View view) {
        Intent i  = new Intent(successful_reseting_password.this, log_in.class);
        startActivity(i);
    }
}
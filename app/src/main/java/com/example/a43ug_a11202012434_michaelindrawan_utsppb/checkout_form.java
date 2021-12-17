package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class checkout_form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_form);
    }
    public void toCheckOutDone(View view){
        Intent i = new Intent(checkout_form.this, checkout_done.class);
        startActivity(i);
    }
}
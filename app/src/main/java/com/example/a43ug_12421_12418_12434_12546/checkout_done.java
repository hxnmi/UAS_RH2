package com.example.a43ug_12421_12418_12434_12546;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class checkout_done extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_done);
    }
    public void toMainMenu(View view){
        Intent i = new Intent(checkout_done.this, main_menu.class);
        startActivity(i);
    }
}
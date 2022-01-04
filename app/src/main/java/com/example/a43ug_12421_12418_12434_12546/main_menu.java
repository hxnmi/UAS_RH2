package com.example.a43ug_12421_12418_12434_12546;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class main_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }
    public void toTables(View view){
        Intent i = new Intent(main_menu.this, tables.class);
        startActivity(i);
    }
    public void toHWStore(View view){
        Intent i = new Intent(main_menu.this, hot_wheels_store.class);
        startActivity(i);
    }
    public void toAbout(View view){
        Intent i = new Intent(main_menu.this, about_dev_main.class);
        startActivity(i);
    }
}
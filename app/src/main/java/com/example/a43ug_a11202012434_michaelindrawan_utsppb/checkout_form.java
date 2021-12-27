package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class checkout_form extends AppCompatActivity {

    String tPrice;
    TextView totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_form);
        totalPrice = (TextView) findViewById(R.id.totalPrice);
        getData();
        setData();
    }
    public void toCheckOutDone(View view){
        Intent i = new Intent(checkout_form.this, checkout_done.class);
        startActivity(i);
    }


    private void getData(){
        if(getIntent().hasExtra("tPrice")){
            tPrice = getIntent().getStringExtra("tPrice");
        }
    }

    public void setData(){
        totalPrice.setText("$ "+tPrice);
    }
}
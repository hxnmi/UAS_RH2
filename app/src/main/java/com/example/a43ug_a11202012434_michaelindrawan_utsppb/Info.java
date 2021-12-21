package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Info extends AppCompatActivity {
    ImageView mainImageView;
    TextView ti,Des;
    String data1,data2;
    int myImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);//nama activity

        mainImageView=findViewById(R.id.mainImageView);//gambar
        ti=findViewById(R.id.nama);//variabel tulisan

        getData();
        setData();

    }



    private void getData(){
        if(getIntent().hasExtra("myImage")&& getIntent().hasExtra("data1") &&
                getIntent().hasExtra("data2")){
            data1=getIntent().getStringExtra("data1");
            myImage=getIntent().getIntExtra("myImage",1);
        }
        else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
    private void setData(){
        ti.setText(data1);
        mainImageView.setImageResource(myImage);

    }





}

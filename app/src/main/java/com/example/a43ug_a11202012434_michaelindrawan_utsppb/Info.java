package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Info extends AppCompatActivity {
    ImageView mainImageView;
    TextView nam,debut,pro,desg,desc;
    String data1,data2,data3,data4,data5;
    int myImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);//nama activity

        mainImageView=findViewById(R.id.mainImageView);//gambar
        nam=findViewById(R.id.nama);//variabel tulisan
        debut=findViewById(R.id.debut);
        pro=findViewById(R.id.produced);
        desg=findViewById(R.id.design);
        desc=findViewById(R.id.desc);
        getData();
        setData();

    }



    private void getData(){
        if(getIntent().hasExtra("myImage")&&
                getIntent().hasExtra("nama") &&
                getIntent().hasExtra("debut")&&
                getIntent().hasExtra("prod") &&
                getIntent().hasExtra("desg") &&
                getIntent().hasExtra("desc")){
            data1=getIntent().getStringExtra("nama");
            data2=getIntent().getStringExtra("debut");
            data3=getIntent().getStringExtra("prod");
            data4=getIntent().getStringExtra("desg");
            data5=getIntent().getStringExtra("desc");

            myImage=getIntent().getIntExtra("myImage",1);
        }
        else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
    private void setData(){
        nam.setText(data1);
        debut.setText(data2);
        pro.setText(data3);
        desg.setText(data4);
        desc.setText(data5);
        mainImageView.setImageResource(myImage);

    }





}

package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.text.DecimalFormat;

public class hot_wheels_store extends AppCompatActivity {
    RecyclerView recyclerV;
    Button checkOut;

    String s1[],s2 [],s3 [],s4 [],s5 [],s6[];
    double harga[]={99.99,99.99,9.99,9.99,9.99,1.49,1.49,1.49,1.49,1.49};
    int jumlahBeli[]={
            0,0,0,0,0,0,0,0,0,0
    };
    int images[]={
            R.drawable.hw_dragonblaster,
            R.drawable.hw_rodgerdodger,
            R.drawable.hw_2020koenigseggjesko,
            R.drawable.hw_winningformula,
            R.drawable.hw_powerrocket,
            R.drawable.hw_fordmustanggt,
            R.drawable.hw_triceratruck,
            R.drawable.hw_totaldisposal,
            R.drawable.hw_rollertoaster,
            R.drawable.hw_chevyelcamino
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_wheels_store);
        recyclerV= findViewById(R.id.HotWheelsStore);
        s1=getResources().getStringArray(R.array.car_name);
        s2=getResources().getStringArray(R.array.harga);
        s3=getResources().getStringArray(R.array.debut);
        s4=getResources().getStringArray(R.array.produced);
        s5=getResources().getStringArray(R.array.designer);
        s6=getResources().getStringArray(R.array.desc);
        checkOut = (Button) findViewById(R.id.checkOut);

        Adapter myAdapter=new Adapter(this,s1,s2,s3,s4,s5,s6,jumlahBeli,images, harga);
        recyclerV.setAdapter(myAdapter);
        recyclerV.setLayoutManager(new LinearLayoutManager(this));
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!myAdapter.checkOutValidate()){
                    Toast.makeText(getApplicationContext(), "Troli Kosong!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(getApplicationContext(), checkout_form.class);
                    DecimalFormat numberFormat = new DecimalFormat("#.00");
                    i.putExtra("tPrice", numberFormat.format(myAdapter.checkOutPrice()));
                    Toast.makeText(getApplicationContext(), " "+numberFormat.format(myAdapter.checkOutPrice()), Toast.LENGTH_SHORT).show();
                    getApplicationContext().startActivity(i);
                }
            }
        });



    }

}
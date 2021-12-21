package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class hot_wheels_store extends AppCompatActivity {
    RecyclerView recyclerV;

    String s1[],s2 [],s3 [],s4 [],s5 [],s6[];
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

        Adapter myAdapter=new Adapter(this,s1,s2,s3,s4,s5,s6,images);
        recyclerV.setAdapter(myAdapter);
        recyclerV.setLayoutManager(new LinearLayoutManager(this));

    }

    public void toCheckOut(View view){
        Intent i = new Intent(hot_wheels_store.this,checkout_form.class);
        startActivity(i);
    }
}
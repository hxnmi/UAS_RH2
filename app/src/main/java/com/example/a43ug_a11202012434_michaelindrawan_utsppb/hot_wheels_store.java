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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_wheels_store);
        String Hw_List[] = {"Dragon Blaster",
                "Rodger Dodger",
                "2020 Koenigsegg Jesko",
                "Winning Formula",
                "Power Rocket",
                "Ford Mustang GT",
                "Tricera Truck",
                "Total Disposal",
                "Roller Toaster",
                "Chevy El Camino"};
        String Hw_Price[] ={"$99.99","$99.99", "$9.99","$9.99","$9.99","$1.49","$1.49","$1.49","$1.49","$1.29"};
        int images[] = {R.drawable.hw_dragonblaster, R.drawable.hw_rodgerdodger, R.drawable.hw_2020koenigseggjesko, R.drawable.hw_winningformula, R.drawable.hw_powerrocket, R.drawable.hw_fordmustanggt, R.drawable.hw_triceratruck, R.drawable.hw_totaldisposal, R.drawable.hw_rollertoaster, R.drawable.hw_chevyelcamino};
        RecyclerView hwlist = (RecyclerView) findViewById(R.id.HotWheelsStore);
        hwlist.setLayoutManager(new LinearLayoutManager(this));
        hwlist.setAdapter(new Adapter(Hw_List,Hw_Price, images));

    }
    public void toCheckOut(View view){
        Intent i = new Intent(hot_wheels_store.this,checkout_form.class);
        startActivity(i);
    }

}
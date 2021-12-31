package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tagmanager.Container;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class checkout_form extends AppCompatActivity {

    String tPrice, alamat;
    TextView totalPrice;
    EditText editAddress;
    TextView mustEnteredExp, mustEnteredAddress;
    Button toCheckOutDone;
    Boolean passed;
    RadioGroup radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_form);
        totalPrice = (TextView) findViewById(R.id.totalPrice);
        editAddress = findViewById(R.id.editAddress);
        mustEnteredAddress = findViewById(R.id.mustEnteredDest);
        mustEnteredExp = findViewById(R.id.mustEnteredExp);
        toCheckOutDone = findViewById(R.id.buttonToCheckOutDone);
        this.passed = false;
        radioButton = (RadioGroup) findViewById(R.id.radioGroup1);
        radioButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.checklistPosIrlandia:
                        passed = true;
                        break;
                    case R.id.checklistTUKI:
                        passed = true;
                        break;
                    case R.id.checklistJnJ:
                        passed = true;
                        break;
                }
            }
        });
        toCheckOutDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editAddress.getText().toString())) {
                    mustEnteredAddress.setVisibility(View.VISIBLE);
                    mustEnteredExp.setVisibility(View.GONE);
                } else {
                    if (!passed) {
                        mustEnteredAddress.setVisibility(View.GONE);
                        mustEnteredExp.setVisibility(View.VISIBLE);
                    } else {
                        Intent i = new Intent(checkout_form.this, checkout_done.class);
                        startActivity(i);
                    }
                }
            }
        });
        editAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            Location location = task.getResult();
                            if (location!=null){
                                Geocoder geocoder = new Geocoder(checkout_form.this, Locale.getDefault());

                                try {
                                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                    editAddress.setText((addresses.get(0).getAddressLine(0)).toString().trim());
                                    Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                }

            }
        });
        getData();
        setData();
    }



    private void getData(){
        if(getIntent().hasExtra("tPrice")){
            tPrice = getIntent().getStringExtra("tPrice");
        }
        if(getIntent().hasExtra("alamat")){
            alamat = getIntent().getStringExtra("alamat");
        }
    }

    public void setData(){
        totalPrice.setText("$ "+tPrice);
        mustEnteredAddress.setText(alamat);
    }
}
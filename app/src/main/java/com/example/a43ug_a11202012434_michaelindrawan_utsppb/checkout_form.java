package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import com.google.android.gms.tagmanager.Container;

public class checkout_form extends AppCompatActivity {

    String tPrice;
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
                switch (i){
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
                if(TextUtils.isEmpty(editAddress.getText().toString())){
                    mustEnteredAddress.setVisibility(View.VISIBLE);
                    mustEnteredExp.setVisibility(View.GONE);
                }
                else{
                    if (!passed){
                        mustEnteredAddress.setVisibility(View.VISIBLE);
                        mustEnteredExp.setVisibility(View.GONE);
                    }
                    else {
                        Intent i = new Intent(checkout_form.this, checkout_done.class);
                        startActivity(i);
                    }
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
    }

    public void setData(){
        totalPrice.setText("$ "+tPrice);
    }
}
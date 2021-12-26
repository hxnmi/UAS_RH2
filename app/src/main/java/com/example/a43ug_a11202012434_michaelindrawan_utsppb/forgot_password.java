package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.TransitionManager;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class forgot_password extends AppCompatActivity {
    EditText EditEmailTxt;
    TextView EmailWrong;
    TextView EmailEmpty;
    ViewGroup Container;
    Button BtnSend;
    DatabaseReference reff;
    Users xuser;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        EditEmailTxt = findViewById(R.id.EditEmail);
        EmailEmpty = findViewById(R.id.EmailEmpty);
        EmailWrong = findViewById(R.id.EmailWrong);
        Container = findViewById(R.id.Container);
        fAuth = FirebaseAuth.getInstance();


        BtnSend = findViewById(R.id.SendButton);
        xuser = new Users();
        reff = FirebaseDatabase.getInstance().getReference().child("User");

        BtnSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                sendButton(EditEmailTxt.getText().toString());
            }
        });
    }
    public void sendButton(final String email){
        if(TextUtils.isEmpty(EditEmailTxt.getText().toString().trim())){
            TransitionManager.beginDelayedTransition(Container);
            EmailEmpty.setVisibility(View.VISIBLE);
            EmailWrong.setVisibility(View.GONE);
        }
        else
            if(!isEmailValid(EditEmailTxt.getText().toString().trim())){
                TransitionManager.beginDelayedTransition(Container);
                EmailEmpty.setVisibility(View.GONE);
                EmailWrong.setVisibility(View.VISIBLE);
            }
            else {
                EmailEmpty.setVisibility(View.GONE);
                EmailWrong.setVisibility(View.GONE);
                
                fAuth.sendPasswordResetEmail(email)
                        .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(forgot_password.this, "Reset Code Sent To Your Email.",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(forgot_password.this, reset_password.class);
                                startActivity(i);
                            }
                        })
                        .addOnFailureListener(this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(forgot_password.this, "Error! Reset Code not sent" + e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
            }
    }
    private static boolean isEmailValid(CharSequence email){
        return (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}
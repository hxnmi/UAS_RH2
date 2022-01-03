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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class forgot_password extends AppCompatActivity {
    EditText EditEmailTxt;
    TextView EmailWrong;
    TextView EmailEmpty;
    TextView EmailNotExist;
    ViewGroup Container;
    Button BtnSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        EditEmailTxt = findViewById(R.id.EditEmail);
        EmailEmpty = findViewById(R.id.EmailEmpty);
        EmailWrong = findViewById(R.id.EmailWrong);
        EmailNotExist = findViewById(R.id.EmailNotExist);
        Container = findViewById(R.id.Container);

        BtnSend = findViewById(R.id.SendButton);
        BtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendButton(EditEmailTxt.getText().toString());
            }
        });
    }
    public void sendButton(final String email){

        Query reff = FirebaseDatabase.getInstance().getReference().child("User").orderByChild("email").equalTo(email);
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    EmailEmpty.setVisibility(View.GONE);
                    EmailWrong.setVisibility(View.GONE);
                    EmailNotExist.setVisibility(View.GONE);

                    Intent i = new Intent(forgot_password.this, reset_password.class);
                    i.putExtra("email", email);
                    startActivity(i);
                    finish();
                } else if (TextUtils.isEmpty(EditEmailTxt.getText().toString().trim())) {
                    TransitionManager.beginDelayedTransition(Container);
                    EmailEmpty.setVisibility(View.VISIBLE);
                    EmailWrong.setVisibility(View.GONE);
                    EmailNotExist.setVisibility(View.GONE);
                } else if (!isEmailValid(EditEmailTxt.getText().toString().trim())) {
                    TransitionManager.beginDelayedTransition(Container);
                    EmailEmpty.setVisibility(View.GONE);
                    EmailWrong.setVisibility(View.VISIBLE);
                    EmailNotExist.setVisibility(View.GONE);
                } else {
                    EmailEmpty.setVisibility(View.GONE);
                    EmailWrong.setVisibility(View.GONE);
                    EmailNotExist.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private static boolean isEmailValid(CharSequence email){
        return (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}


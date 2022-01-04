package com.example.a43ug_12421_12418_12434_12546;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class forgot_password extends AppCompatActivity {
    EditText EditEmailTxt;
    ViewGroup Container;
    Button BtnSend;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        EditEmailTxt = findViewById(R.id.EditEmail);
        Container = findViewById(R.id.Container);
        fAuth = FirebaseAuth.getInstance();

        BtnSend = findViewById(R.id.SendButton);
        BtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendButton(EditEmailTxt.getText().toString());
            }
        });
    }
    public void sendButton(final String email){

        fAuth.sendPasswordResetEmail(email);
        Query reff = FirebaseDatabase.getInstance().getReference().child("User").orderByChild("email").equalTo(email);
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(forgot_password.this, "Enter verification code sent to your email", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(forgot_password.this, reset_password.class);
                    i.putExtra("email", email);
                    startActivity(i);
                    finish();
                } else if (TextUtils.isEmpty(EditEmailTxt.getText().toString().trim())) {
                    TransitionManager.beginDelayedTransition(Container);
                    Toast.makeText(forgot_password.this, "Email can't be empty!", Toast.LENGTH_SHORT).show();
                } else if (!isEmailValid(EditEmailTxt.getText().toString().trim())) {
                    TransitionManager.beginDelayedTransition(Container);
                    Toast.makeText(forgot_password.this, "Your email is Incorrect!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(forgot_password.this, "Your email does not Exist!", Toast.LENGTH_SHORT).show();
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


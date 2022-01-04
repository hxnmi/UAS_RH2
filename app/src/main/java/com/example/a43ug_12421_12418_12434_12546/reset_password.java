package com.example.a43ug_12421_12418_12434_12546;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class reset_password extends AppCompatActivity {
    EditText Edit6DigitsCode;
    EditText EditPassword;
    EditText EditNewPassword;
    EditText ConfirmNewPassword;
    ViewGroup Container;
    Button BtnConfirm;
    FirebaseAuth fAuth;
    FirebaseAuth.AuthStateListener fStateListener;


    private static final String TAG = reset_password.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        Edit6DigitsCode = findViewById(R.id.Edit6DigitsCode);
        EditPassword = findViewById(R.id.EditPassword);
        EditNewPassword = findViewById(R.id.EditNewPassword);
        ConfirmNewPassword = findViewById(R.id.ConfirmNewPassword);
        Container = findViewById(R.id.Container);
        fAuth = FirebaseAuth.getInstance();

        fStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                }
                else {
                    Log.d(TAG, "onAuthStateChanged:signed_out:");
                }
            }
        };



        BtnConfirm = findViewById(R.id.ConfirmButton);
        BtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword(EditNewPassword.getText().toString());
            }
        });
    }


    public void resetPassword(final String newPassword){


        if(TextUtils.isEmpty(Edit6DigitsCode.getText().toString().trim())){
            TransitionManager.beginDelayedTransition(Container);
            Toast.makeText(reset_password.this, "Code must be filled!", Toast.LENGTH_SHORT).show();
        }

        else if((TextUtils.isEmpty(EditNewPassword.getText().toString().trim()))||TextUtils.isEmpty(ConfirmNewPassword.getText().toString().trim())){
            TransitionManager.beginDelayedTransition(Container);
            Toast.makeText(reset_password.this, "Password and Confirm Password can't be empty!", Toast.LENGTH_SHORT).show();
        }
        else if(!(EditNewPassword.getText().toString().trim()).equals(ConfirmNewPassword.getText().toString().trim())) {
            TransitionManager.beginDelayedTransition(Container);
            Toast.makeText(reset_password.this, "Your Password does not match!", Toast.LENGTH_SHORT).show();
        }
        else if(Edit6DigitsCode.getText().toString().trim().length() != 6) {
            TransitionManager.beginDelayedTransition(Container);
            Toast.makeText(reset_password.this, "Code must be 6 digits!", Toast.LENGTH_SHORT).show();
        }
        else if (Edit6DigitsCode.getText().toString().trim() != "696969") {
            TransitionManager.beginDelayedTransition(Container);
            Toast.makeText(reset_password.this, "The entered code is incorrect.", Toast.LENGTH_SHORT).show();
        }
        else{
            String email = getIntent().getStringExtra("email");
            DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("User");
            Query query = reff.orderByChild("email").equalTo(email);
            query.addChildEventListener(
                    new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            reff.child(dataSnapshot.getKey()).child("password").setValue(newPassword);
                            Intent i = new Intent(reset_password.this, successful_reseting_password.class);
                            startActivity(i);
                            finish();
                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        }
                    });
         }
    }

    @Override
    protected void onStart() {
        super.onStart();
        fAuth.addAuthStateListener(fStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (fStateListener != null) {
            fAuth.removeAuthStateListener(fStateListener);
        }
    }

}

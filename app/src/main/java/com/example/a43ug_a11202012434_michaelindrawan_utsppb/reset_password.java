package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class reset_password extends AppCompatActivity {
    EditText Edit6DigitsCode;
    EditText EditPassword;
    EditText EditNewPassword;
    EditText ConfirmNewPassword;
    TextView PasswordandCodeEmpty;
    TextView PasswordnotMatch;
    ViewGroup Container;
    TextView CodenotMatch;
    Button BtnConfirm;
    DatabaseReference reff;
    FirebaseUser user;
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
        PasswordandCodeEmpty= findViewById(R.id.PasswordandCodeEmpty);
        PasswordnotMatch = findViewById(R.id.PasswordnotMatch);
        CodenotMatch = findViewById(R.id.CodenotMatch);
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


        user = fAuth.getCurrentUser();
        reff = FirebaseDatabase.getInstance().getReference().child("User");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                String oldPassword = dataSnapshot.child("password").getValue().toString();

                BtnConfirm.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        resetPassword(oldPassword, EditPassword.getText().toString(),EditNewPassword.getText().toString());
                    }

                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }

    public void resetPassword(String oldPassword,String edtPassword, final String newPassword){


        if((TextUtils.isEmpty(Edit6DigitsCode.getText().toString().trim()))||(TextUtils.isEmpty(EditNewPassword.getText().toString().trim()))||TextUtils.isEmpty(ConfirmNewPassword.getText().toString().trim())){
            TransitionManager.beginDelayedTransition(Container);
            PasswordandCodeEmpty.setVisibility(View.VISIBLE);
            PasswordnotMatch.setVisibility(View.GONE);
            CodenotMatch.setVisibility(View.GONE);
        }
        else
        if(!(EditNewPassword.getText().toString().trim()).equals(ConfirmNewPassword.getText().toString().trim())) {
            TransitionManager.beginDelayedTransition(Container);
            PasswordandCodeEmpty.setVisibility(View.GONE);
            PasswordnotMatch.setVisibility(View.VISIBLE);
            CodenotMatch.setVisibility(View.GONE);
        }
        else {
            if (Edit6DigitsCode.getText().toString().trim().length() != 6) {
                TransitionManager.beginDelayedTransition(Container);
                PasswordandCodeEmpty.setVisibility(View.GONE);
                PasswordnotMatch.setVisibility(View.GONE);
                CodenotMatch.setVisibility(View.VISIBLE);
            }
            else if(edtPassword.equals(oldPassword)){
                PasswordandCodeEmpty.setVisibility(View.GONE);
                PasswordnotMatch.setVisibility(View.GONE);
                CodenotMatch.setVisibility(View.GONE);


                    user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "Password updated");
                                Intent i = new Intent(reset_password.this, successful_reseting_password.class);
                                startActivity(i);
                            } else {
                                Log.d(TAG, "Error password not updated");
                            }
                        }
                    });

                 }
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

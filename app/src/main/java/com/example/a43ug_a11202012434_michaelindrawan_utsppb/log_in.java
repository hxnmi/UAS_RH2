package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.TransitionManager;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class log_in extends AppCompatActivity {
    ViewGroup Container;
    TextView WrongEmail;
    TextView EmailandPasswordEmpty;
    TextView EmailPasswordNotExist;
    EditText EditEmail;
    EditText EditPassword;
    Button BtnLogin;

    private FirebaseAuth fAuth;

    private static final String TAG = log_in.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        fAuth = FirebaseAuth.getInstance();
        WrongEmail = findViewById(R.id.WrongEmail);
        EmailandPasswordEmpty = findViewById(R.id.EmailPasswordisEmpty);
        EmailPasswordNotExist = findViewById(R.id.EmailPasswordNotExist);
        EditEmail = findViewById(R.id.EditEmail);
        EditPassword = findViewById(R.id.EditPassword);
        Container = findViewById(R.id.Container);
        BtnLogin = findViewById(R.id.LoginButton);

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(EditEmail.getText().toString(), EditPassword.getText().toString());
            }
        });
    }

    public void forgotPassword(View view){
        Intent i = new Intent(log_in.this, forgot_password.class);
        startActivity(i);
    }

    public void signup(View view){
        Intent i = new Intent(log_in.this, sign_up.class);
        startActivity(i);
    }

    public void signIn(final String email, String password){
     if((TextUtils.isEmpty(EditEmail.getText().toString().trim()))||(TextUtils.isEmpty(EditPassword.getText().toString().trim()))){
         TransitionManager.beginDelayedTransition(Container);
         EmailandPasswordEmpty.setVisibility(View.VISIBLE);
         WrongEmail.setVisibility(View.GONE);
         EmailPasswordNotExist.setVisibility(View.GONE);

     }
     else
         if(!isValidEmail(EditEmail.getText().toString().trim())){
             TransitionManager.beginDelayedTransition(Container);
             EmailandPasswordEmpty.setVisibility(View.GONE);
             EmailPasswordNotExist.setVisibility(View.GONE);
             WrongEmail.setVisibility(View.VISIBLE);
         }
         else{
             EmailandPasswordEmpty.setVisibility(View.GONE);
             WrongEmail.setVisibility(View.GONE);
             DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("User");
             Query query = reff.orderByChild("email").equalTo(email);
             query.addListenerForSingleValueEvent(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     if(dataSnapshot.exists()){
                         for (DataSnapshot user : dataSnapshot.getChildren()) {
                             String password1 = user.child("password").getValue(String.class);
                             if (password1.equals(password)) {
                                 Toast.makeText(log_in.this, "Login Success!", Toast.LENGTH_SHORT).show();
                                 Intent i = new Intent(log_in.this, main_menu.class);
                                 i.putExtra("email", email);
                                 startActivity(i);
                             } else {
                                 Toast.makeText(log_in.this, "Password is wrong", Toast.LENGTH_SHORT).show();
                             }
                         }
                     } else {
                         EmailPasswordNotExist.setVisibility(View.VISIBLE);
                     }

                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError error) {

                 }
             });

        }
    }

    public static boolean isValidEmail(CharSequence email){
        return  (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}

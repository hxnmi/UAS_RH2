package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class log_in extends AppCompatActivity {
    ViewGroup Container;
    TextView WrongEmail;
    TextView EmailandPasswordEmpty;
    EditText EditEmail;
    EditText EditPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        WrongEmail = findViewById(R.id.WrongEmail);
        EmailandPasswordEmpty = findViewById(R.id.EmailPasswordisEmpty);
        EditEmail = findViewById(R.id.EditEmail);
        EditPassword = findViewById(R.id.EditPassword);
        Container = findViewById(R.id.Container);

    }
    public void forgotPassword(View view){
        Intent i = new Intent(log_in.this, forgot_password.class);
        startActivity(i);
    }

    public void loginButton(View view){
     if((TextUtils.isEmpty(EditEmail.getText().toString().trim()))||(TextUtils.isEmpty(EditPassword.getText().toString().trim()))){
         TransitionManager.beginDelayedTransition(Container);
         EmailandPasswordEmpty.setVisibility(View.VISIBLE);
         WrongEmail.setVisibility(View.GONE);

     }
     else
         if(!isValidEmail(EditEmail.getText().toString().trim())){
             TransitionManager.beginDelayedTransition(Container);
             EmailandPasswordEmpty.setVisibility(View.GONE);
             WrongEmail.setVisibility(View.VISIBLE);
         }
         else{
             EmailandPasswordEmpty.setVisibility(View.GONE);
             WrongEmail.setVisibility(View.GONE);
             Intent i = new Intent(log_in.this, main_menu.class);
             startActivity(i);
         }
    }

    public static boolean isValidEmail(CharSequence email){
        return  (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}

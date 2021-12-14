package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

import androidx.activity.contextaware.ContextAware;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.TransitionManager;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class forgot_password extends AppCompatActivity {
    EditText EditEmailTxt;
    TextView EmailWrong;
    TextView EmailEmpty;
    ViewGroup Container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        EditEmailTxt = findViewById(R.id.EditEmail);
        EmailEmpty = findViewById(R.id.EmailEmpty);
        EmailWrong = findViewById(R.id.EmailWrong);
        Container = findViewById(R.id.Container);
    }
    public void sendButton(View view){
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
                Intent i = new Intent(forgot_password.this, reset_password.class);
                startActivity(i);
            }
    }
    private static boolean isEmailValid(CharSequence email){
        return (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}
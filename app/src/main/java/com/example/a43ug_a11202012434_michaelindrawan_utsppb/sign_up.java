package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

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

public class sign_up extends AppCompatActivity {
    ViewGroup Container;
    TextView WrongEmail;
    TextView PasswordnotMatch;
    TextView NameEmpty;
    TextView EmailEmpty;
    TextView PasswordEmpty;
    TextView ConfirmPasswordEmpty;
    EditText EditFirstName;
    EditText EditLastName;
    EditText EditEmail;
    EditText EditPassword;
    EditText EditConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        WrongEmail = findViewById(R.id.WrongEmail);
        PasswordnotMatch = findViewById(R.id.PasswordnotMatch);
        NameEmpty = findViewById(R.id.NameEmpty);
        EmailEmpty = findViewById(R.id.EmailEmpty);
        PasswordEmpty = findViewById(R.id.PasswordEmpty);
        ConfirmPasswordEmpty = findViewById(R.id.ConfirmPasswordEmpty);
        EditFirstName = findViewById(R.id.EditFirstName);
        EditLastName = findViewById(R.id.EditLastName);
        EditEmail = findViewById(R.id.EditEmail);
        EditPassword = findViewById(R.id.EditPassword);
        EditConfirmPassword = findViewById(R.id.EditConfirmPassword);
        Container = findViewById(R.id.Container);

    }

    public void signupButton(View view){
        if((TextUtils.isEmpty(EditFirstName.getText().toString().trim()))||(TextUtils.isEmpty(EditLastName.getText().toString().trim()))){
            TransitionManager.beginDelayedTransition(Container);
            NameEmpty.setVisibility(View.VISIBLE);
            EmailEmpty.setVisibility(View.GONE);
            PasswordEmpty.setVisibility(View.GONE);
            ConfirmPasswordEmpty.setVisibility(View.GONE);
            WrongEmail.setVisibility(View.GONE);
            PasswordnotMatch.setVisibility(View.GONE);

        }else
        if(TextUtils.isEmpty(EditEmail.getText().toString().trim())){
            TransitionManager.beginDelayedTransition(Container);
            NameEmpty.setVisibility(View.GONE);
            EmailEmpty.setVisibility(View.VISIBLE);
            PasswordEmpty.setVisibility(View.GONE);
            ConfirmPasswordEmpty.setVisibility(View.GONE);
            WrongEmail.setVisibility(View.GONE);
            PasswordnotMatch.setVisibility(View.GONE);
        }
        else
        if(!isValidEmail(EditEmail.getText().toString().trim())){
            TransitionManager.beginDelayedTransition(Container);
            NameEmpty.setVisibility(View.GONE);
            EmailEmpty.setVisibility(View.GONE);
            PasswordEmpty.setVisibility(View.GONE);
            ConfirmPasswordEmpty.setVisibility(View.GONE);
            WrongEmail.setVisibility(View.VISIBLE);
            PasswordnotMatch.setVisibility(View.GONE);
        }
        else
        if(TextUtils.isEmpty(EditPassword.getText().toString().trim())){
            TransitionManager.beginDelayedTransition(Container);
            NameEmpty.setVisibility(View.GONE);
            EmailEmpty.setVisibility(View.GONE);
            PasswordEmpty.setVisibility(View.VISIBLE);
            ConfirmPasswordEmpty.setVisibility(View.GONE);
            WrongEmail.setVisibility(View.GONE);
            PasswordnotMatch.setVisibility(View.GONE);
        }
        else
        if(TextUtils.isEmpty(EditConfirmPassword.getText().toString().trim())){
            TransitionManager.beginDelayedTransition(Container);
            NameEmpty.setVisibility(View.GONE);
            EmailEmpty.setVisibility(View.GONE);
            PasswordEmpty.setVisibility(View.GONE);
            ConfirmPasswordEmpty.setVisibility(View.VISIBLE);
            WrongEmail.setVisibility(View.GONE);
            PasswordnotMatch.setVisibility(View.GONE);
        }
        else
        if(!EditPassword.getText().toString().trim().equals(EditConfirmPassword.getText().toString().trim())){
            TransitionManager.beginDelayedTransition(Container);
            NameEmpty.setVisibility(View.GONE);
            EmailEmpty.setVisibility(View.GONE);
            PasswordEmpty.setVisibility(View.GONE);
            ConfirmPasswordEmpty.setVisibility(View.GONE);
            WrongEmail.setVisibility(View.GONE);
            PasswordnotMatch.setVisibility(View.VISIBLE);
        }
        else{
            NameEmpty.setVisibility(View.GONE);
            EmailEmpty.setVisibility(View.GONE);
            PasswordEmpty.setVisibility(View.GONE);
            ConfirmPasswordEmpty.setVisibility(View.GONE);
            WrongEmail.setVisibility(View.GONE);
            PasswordnotMatch.setVisibility(View.GONE);
            Intent i = new Intent(sign_up.this, main_menu.class);
            startActivity(i);
        }
    }

    public static boolean isValidEmail(CharSequence email){
        return  (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}
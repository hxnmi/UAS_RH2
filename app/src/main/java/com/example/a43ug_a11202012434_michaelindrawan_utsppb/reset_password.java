package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class reset_password extends AppCompatActivity {
    EditText Edit6DigitsCode;
    EditText EditNewPassword;
    EditText ConfirmNewPassword;
    TextView PasswordandCodeEmpty;
    TextView PasswordnotMatch;
    ViewGroup Container;
    TextView CodenotMatch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        Edit6DigitsCode = findViewById(R.id.Edit6DigitsCode);
        EditNewPassword = findViewById(R.id.EditNewPassword);
        ConfirmNewPassword = findViewById(R.id.ConfirmNewPassword);
        PasswordandCodeEmpty= findViewById(R.id.PasswordandCodeEmpty);
        PasswordnotMatch = findViewById(R.id.PasswordnotMatch);
        CodenotMatch = findViewById(R.id.CodenotMatch);
        Container = findViewById(R.id.Container);
    }
    public void resetPassword(View view){
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
            else{
                PasswordandCodeEmpty.setVisibility(View.GONE);
                PasswordnotMatch.setVisibility(View.GONE);
                CodenotMatch.setVisibility(View.GONE);
                Intent i = new Intent(reset_password.this, successful_reseting_password.class);
                startActivity(i);
            }

        }
    }
}

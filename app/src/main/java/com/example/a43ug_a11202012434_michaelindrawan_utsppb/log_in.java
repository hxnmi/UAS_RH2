package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.Transition;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class log_in extends AppCompatActivity {
    ViewGroup Container;
    TextView WrongEmail;
    TextView EmailandPasswordEmpty;
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
             fAuth.signInWithEmailAndPassword(email, password)
                     .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                             if (!task.isSuccessful()) {
                                 Log.w(TAG, "signInWithEmail:failed", task.getException());
                                 Toast toast = Toast.makeText(log_in.this, "Login Failed!\nInvalid Email or Password", Toast.LENGTH_LONG);
                                 LinearLayout layout = (LinearLayout) toast.getView();
                                 if (layout.getChildCount() > 0) {
                                     TextView tv = (TextView) layout.getChildAt(0);
                                     tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                                 }
                                 toast.show();
                             }
                             else{
                                 Toast.makeText(log_in.this, "Login Success!", Toast.LENGTH_SHORT).show();
                                 Intent i = new Intent(log_in.this, main_menu.class);
                                 startActivity(i);
                             }
                         }
                     });
         }
    }

    public static boolean isValidEmail(CharSequence email){
        return  (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

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
    Button BtnSignUp;
    DatabaseReference reff;
    Users xuser;
    FirebaseAuth fAuth;
    FirebaseAuth.AuthStateListener fStateListener;

    private static final String TAG = sign_up.class.getSimpleName();

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
        EditConfirmPassword = findViewById(R.id.EditConfirmPassword);
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

        BtnSignUp = findViewById(R.id.SignupButton);
        EditEmail = findViewById(R.id.EditEmail);
        EditPassword = findViewById(R.id.EditPassword);
        xuser = new Users();
        reff = FirebaseDatabase.getInstance().getReference().child("User");

        BtnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                signUp(EditEmail.getText().toString(), EditPassword.getText().toString());
            }
        });
    }

    public void signUp(final String email, String password){
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

            fAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                            if(!task.isSuccessful()){
                                Objects.requireNonNull(task.getException()).printStackTrace();
                                Toast toast = Toast.makeText(sign_up.this, "Sign Up Failed!\nPassword At Least 6 Characters", Toast.LENGTH_LONG);
                                LinearLayout layout = (LinearLayout) toast.getView();
                                if (layout.getChildCount() > 0) {
                                    TextView tv = (TextView) layout.getChildAt(0);
                                    tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                                }
                                toast.show();
                            }
                            else{
                                Toast.makeText(sign_up.this, "Sign Up Success!", Toast.LENGTH_SHORT).show();
                                xuser.SetFName(EditFirstName.getText().toString().trim());
                                xuser.SetLName(EditLastName.getText().toString().trim());
                                xuser.SetEmail(EditEmail.getText().toString().trim());
                                xuser.SetPassword(EditPassword.getText().toString().trim());
                                reff.push().setValue(xuser);
                                Intent i = new Intent(sign_up.this, log_in.class);
                                startActivity(i);
                            }
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

    public static boolean isValidEmail(CharSequence email){
        return  (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}
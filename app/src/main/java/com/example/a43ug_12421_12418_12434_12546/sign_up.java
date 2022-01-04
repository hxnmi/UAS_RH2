package com.example.a43ug_12421_12418_12434_12546;

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
    EditText EditFirstName;
    EditText EditLastName;
    EditText EditEmail;
    EditText EditPassword;
    EditText EditConfirmPassword;
    EditText EditPhone;
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

        EditFirstName = findViewById(R.id.EditFirstName);
        EditLastName = findViewById(R.id.EditLastName);
        EditConfirmPassword = findViewById(R.id.EditConfirmPassword);
        EditPhone = findViewById(R.id.EditPhone);
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
            Toast.makeText(sign_up.this, "Name can't be empty!", Toast.LENGTH_SHORT).show();
        }else
        if(TextUtils.isEmpty(EditEmail.getText().toString().trim())){
            TransitionManager.beginDelayedTransition(Container);
            Toast.makeText(sign_up.this, "Email can't be empty!", Toast.LENGTH_SHORT).show();
        }
        else
        if(!isValidEmail(EditEmail.getText().toString().trim())){
            TransitionManager.beginDelayedTransition(Container);
            Toast.makeText(sign_up.this, "Your email is Incorrect!", Toast.LENGTH_SHORT).show();
        }
        else
        if(TextUtils.isEmpty(EditPassword.getText().toString().trim())){
            TransitionManager.beginDelayedTransition(Container);
            Toast.makeText(sign_up.this, "Your Password can't be empty!", Toast.LENGTH_SHORT).show();
        }
        else
        if(TextUtils.isEmpty(EditConfirmPassword.getText().toString().trim())){
            TransitionManager.beginDelayedTransition(Container);
            Toast.makeText(sign_up.this, "Confirm Password can't be empty!", Toast.LENGTH_SHORT).show();
        }
        else
        if(!EditPassword.getText().toString().trim().equals(EditConfirmPassword.getText().toString().trim())){
            TransitionManager.beginDelayedTransition(Container);
            Toast.makeText(sign_up.this, "Your Password does not match!", Toast.LENGTH_SHORT).show();
        }
        else
        if(TextUtils.isEmpty(EditPhone.getText().toString().trim())){
            TransitionManager.beginDelayedTransition(Container);
            Toast.makeText(sign_up.this, "Phone can't be empty!", Toast.LENGTH_SHORT).show();
        }
        else{
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
                                xuser.SetPhone(EditPhone.getText().toString().trim());
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
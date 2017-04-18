package com.khushmeetsingh.travelsafe;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    protected EditText signup_email;
    protected EditText signup_pswd;
    private FirebaseAuth mFirebaseAuth;
    protected ProgressBar signupBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mFirebaseAuth = FirebaseAuth.getInstance();
        signup_email = (EditText)findViewById(R.id.signup_email);
        signup_pswd = (EditText)findViewById(R.id.signup_pswd);
        signupBar = (ProgressBar)findViewById(R.id.signup_bar);

    }

    public void onSignUp(View v){
        String email = signup_email.getText().toString();
        String pswd = signup_pswd.getText().toString();

        if (pswd.isEmpty() || email.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
            builder.setMessage("Cannot create account")
                    .setTitle("Error")
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            signupBar.setVisibility(View.VISIBLE);
            mFirebaseAuth.createUserWithEmailAndPassword(email, pswd)
                    .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(SignUp.this, HomeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                                builder.setMessage(task.getException().getMessage())
                                        .setTitle("Error")
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                                signupBar.setVisibility(View.GONE);
                            }
                        }
                    });
        }

    }

}

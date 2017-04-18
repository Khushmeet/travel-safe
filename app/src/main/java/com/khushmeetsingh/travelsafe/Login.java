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

public class Login extends AppCompatActivity {

    protected EditText email;
    protected EditText pswd;
    private FirebaseAuth mFirebaseAuth;
    protected ProgressBar login_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText)findViewById(R.id.login_email);
        pswd = (EditText)findViewById(R.id.login_pswd);
        mFirebaseAuth = FirebaseAuth.getInstance();
        login_bar = (ProgressBar)findViewById(R.id.login_bar);
    }

    public void doLogin(View v){
        String emailStr = email.getText().toString();
        String pswdStr = pswd.getText().toString();
        if (emailStr.isEmpty() || pswdStr.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
            builder.setMessage("Please enter the details")
                    .setTitle("Missing")
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else{
            login_bar.setVisibility(View.VISIBLE);
            mFirebaseAuth.signInWithEmailAndPassword(emailStr, pswdStr)
                    .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(Login.this, HomeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                builder.setMessage(task.getException().getMessage())
                                        .setTitle("Cannot Login")
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                                login_bar.setVisibility(View.GONE);
                            }
                        }
                    });
        }
    }
}

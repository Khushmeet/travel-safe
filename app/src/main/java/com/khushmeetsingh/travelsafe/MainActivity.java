package com.khushmeetsingh.travelsafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View v){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    public void signUp(View v){
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }
}

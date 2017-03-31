package com.khushmeetsingh.travelsafe;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void homeActivity(View v){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}

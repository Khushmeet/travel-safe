package com.khushmeetsingh.travelsafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.katepratik.msg91api.MSG91;

public class Message extends AppCompatActivity {
    protected String qr_data;
    protected TextView qrView;
    protected EditText guardian;
    protected ProgressBar notify_bar;
    private FirebaseAuth mFirebaseAuth;
    MSG91 msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        mFirebaseAuth = FirebaseAuth.getInstance();
        qrView = (TextView)findViewById(R.id.qrData);
        guardian = (EditText)findViewById(R.id.guardian);
        notify_bar = (ProgressBar)findViewById(R.id.notify_bar);
        Bundle qrData = getIntent().getExtras();
        qr_data = qrData.getString("qr_data");

        qrView.setText("Driver Details:\n"+qr_data);

        msg = new MSG91("149404AiLmf4H5nHI58f5df98", true);
    }

    public void notify(View v){
        String guardian_phone = guardian.getText().toString();
        msg.composeMessage("TRVLSF",qr_data);
        msg.to(guardian_phone);
        notify_bar.setVisibility(View.VISIBLE);
        String status = msg.send();
        notify_bar.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), "Message successfully sent", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, HomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        Log.d("TRAVELSAFE",status);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                mFirebaseAuth.signOut();
                Intent i = new Intent(this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

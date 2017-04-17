package com.khushmeetsingh.travelsafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Message extends AppCompatActivity {
    String qr_data;
    TextView qrView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        qrView = (TextView)findViewById(R.id.qrData);
        Bundle qrData = getIntent().getExtras();
        qr_data = qrData.getString("qr_data");

        qrView.setText("Driver Details:\n"+qr_data);
    }
}

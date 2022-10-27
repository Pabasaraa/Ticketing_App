package com.csse.ticketing_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class DashboardActivity extends AppCompatActivity {

    LinearLayoutCompat profileBtn, topUp, payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_dashboard );

        profileBtn = findViewById( R.id.profile_btn );
        topUp = findViewById( R.id.topup_btn );
        payment = findViewById( R.id.payment_btn );

        profileBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), ProfileActivity.class );
                startActivity( intent );
            }
        } );

        topUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), TopUpActivity.class );
                startActivity( intent );
            }
        } );

        payment.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), AddPaymentActivity.class );
                startActivity( intent );
            }
        } );

    }
}
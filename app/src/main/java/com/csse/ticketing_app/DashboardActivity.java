package com.csse.ticketing_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    LinearLayoutCompat profileBtn, topUp, payment;
    TextView balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_dashboard );

        Bundle bundle = getIntent().getExtras();

        profileBtn = findViewById( R.id.profile_btn );
        topUp = findViewById( R.id.dashboard_topup_btn );
        payment = findViewById( R.id.payment_btn );
        balance = findViewById( R.id.dashboard_balance );

        if (bundle == null ) {
            return;
        } else {
            String balanceFromDb = bundle.getString( "balance" );

            balance.setText( balanceFromDb );
        }

        profileBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), ProfileActivity.class );
                intent.putExtras( bundle );
                startActivity( intent );
            }
        });

        topUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), TopUpActivity.class );
                intent.putExtras( bundle );
                startActivity( intent );
            }
        });

        payment.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), AddPaymentActivity.class );
                intent.putExtras( bundle );
                startActivity( intent );
            }
        });

    }
}
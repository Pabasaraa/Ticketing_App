package com.csse.ticketing_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    LinearLayoutCompat profileBtn, topUp, payment;
    LinearLayout logoutBtn;
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
        logoutBtn = findViewById( R.id.log_out );

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

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Logged out", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

    }
}
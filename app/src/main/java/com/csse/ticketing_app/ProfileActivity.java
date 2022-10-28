package com.csse.ticketing_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    ImageView back;
    TextView fullName, username, nic, nameHeading, usernameHeading, mobile, balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_profile );

        Bundle bundle = getIntent().getExtras();

        back = findViewById( R.id.back_btn_profile );
        fullName = findViewById( R.id.profile_full_name );
        username = findViewById( R.id.profile_username );
        nic = findViewById( R.id.profile_nic );
        nameHeading = findViewById( R.id.full_name_heading );
        usernameHeading = findViewById( R.id.username_heading );
        mobile = findViewById( R.id.profile_mobile );
        balance = findViewById( R.id.profile_balance );

        if (bundle == null ) {
            return;
        } else {
            String nameFromDB = bundle.getString( "name" );
            String usernameFromDb = bundle.getString( "username" );
            String nicFromDB = bundle.getString( "nic" );
            String balanceFromDb = bundle.getString( "balance" );

            nameHeading.setText( nameFromDB );
            usernameHeading.setText( usernameFromDb );
            fullName.setText( nameFromDB );
            username.setText( usernameFromDb );
            nic.setText( nicFromDB );
            balance.setText( balanceFromDb );
        }

        back.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
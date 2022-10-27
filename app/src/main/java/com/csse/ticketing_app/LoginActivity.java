package com.csse.ticketing_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button notAMember, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        notAMember = findViewById( R.id.not_a_member_btn );
        login = findViewById( R.id.login_btn );

        notAMember.setOnClickListener( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext (), SignupActivity.class );
                startActivity( intent );
                finish();
            }
        } );

        login.setOnClickListener( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext (), DashboardActivity.class );
                startActivity( intent );
                finish();
            }
        } );

    }
}
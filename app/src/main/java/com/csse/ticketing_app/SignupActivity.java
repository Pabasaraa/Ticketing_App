package com.csse.ticketing_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignupActivity extends AppCompatActivity {

    Button alreadyAMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signup );

        alreadyAMember = findViewById( R.id.have_an_account );

        alreadyAMember.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext (), LoginActivity.class );
                startActivity( intent );
                finish();
            }
        } );

    }
}
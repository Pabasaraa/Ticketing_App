package com.csse.ticketing_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ProfileActivity extends AppCompatActivity {

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_profile );

        back = findViewById( R.id.back_btn_profile );

        back.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
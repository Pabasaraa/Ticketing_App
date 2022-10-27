package com.csse.ticketing_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TopUpActivity extends AppCompatActivity {

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_top_up );

        back = findViewById( R.id.back_btn_top_up );

        back.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
package com.csse.ticketing_app;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AddPaymentActivity extends AppCompatActivity {

    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_payment );

        backBtn = findViewById( R.id.back_btn_payment );

        backBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        } );
    }
}
package com.csse.ticketing_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AddPaymentActivity extends AppCompatActivity {

    ImageView backBtn;
    AppCompatEditText name, cardNum, expiryDate, cvv, mobileNum;
    AppCompatButton addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_payment );

        backBtn = findViewById( R.id.back_btn_payment );
        name = findViewById( R.id.add_payment_name );
        cardNum = findViewById( R.id.add_payment_cardNumber );
        expiryDate = findViewById( R.id.add_payment_expiryDate );
        cvv = findViewById( R.id.add_payment_cvv );
        mobileNum = findViewById( R.id.add_payment_mobileNum );
        addBtn = findViewById( R.id.payment_add_btn );

        backBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        } );
    }
}
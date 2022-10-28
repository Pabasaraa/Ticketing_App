package com.csse.ticketing_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class AddPaymentActivity extends AppCompatActivity {

    ImageView backBtn;
    AppCompatEditText name, cardNum, expiryDate, cvv, mobileNum;
    AppCompatButton addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_payment );

        Bundle bundle = getIntent().getExtras();

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
        });

        addBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ( !validateName() | !validateCardNum() | !validateExpiryDate() | !validateCvv() | !validateMobileNumber() ){
                    return;
                } else {
                    String nameStr = Objects.requireNonNull(name.getText()).toString();
                    String cardNumStr = Objects.requireNonNull(cardNum.getText()).toString();
                    String expiryDateStr = Objects.requireNonNull(expiryDate.getText()).toString();
                    String cvvStr = Objects.requireNonNull(cvv.getText()).toString();
                    String mobileStr = Objects.requireNonNull(mobileNum.getText()).toString();

                    PaymentHelperClass newPayment = new PaymentHelperClass( nameStr, cardNumStr, expiryDateStr, cvvStr, mobileStr );

                    DatabaseReference reference = FirebaseDatabase.getInstance( "https://ticketing-app-89a17-default-rtdb.asia-southeast1.firebasedatabase.app/" ).getReference("users").child(bundle.getString("username")).child(cardNumStr);

                    reference.setValue( newPayment ).addOnSuccessListener(suc -> {
                        Toast.makeText(getApplicationContext(), "Details added!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        startActivity(intent);
                        finish();

                    }).addOnFailureListener ( err -> {
                        Toast.makeText(getApplicationContext(), "" + err.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });

    }

    private Boolean validateName () {
        String nameStr = Objects.requireNonNull( name.getText() ).toString();

        if (nameStr.isEmpty ()) {
            name.setError ( "Fields cannot be empty" );
            return false;
        } else {
            name.setError ( null );
            return true;
        }
    }

    private Boolean validateCardNum () {
        String cardNumStr = Objects.requireNonNull( cardNum.getText() ).toString();

        if (cardNumStr.isEmpty ()) {
            cardNum.setError ( "Fields cannot be empty" );
            return false;
        } else {
            cardNum.setError ( null );
            return true;
        }
    }

    private Boolean validateExpiryDate () {
        String expiryDateStr = Objects.requireNonNull( expiryDate.getText() ).toString();

        if (expiryDateStr.isEmpty ()) {
            expiryDate.setError ( "Fields cannot be empty" );
            return false;
        } else {
            expiryDate.setError ( null );
            return true;
        }
    }

    private Boolean validateCvv () {
        String cvvStr = Objects.requireNonNull( cvv.getText() ).toString();

        if (cvvStr.isEmpty ()) {
            cvv.setError ( "Fields cannot be empty" );
            return false;
        } else {
            cvv.setError ( null );
            return true;
        }
    }

    private Boolean validateMobileNumber () {
        String mobileNumStr = Objects.requireNonNull( mobileNum.getText() ).toString();

        if (mobileNumStr.isEmpty ()) {
            mobileNum.setError ( "Fields cannot be empty" );
            return false;
        } else {
            mobileNum.setError ( null );
            return true;
        }
    }
}
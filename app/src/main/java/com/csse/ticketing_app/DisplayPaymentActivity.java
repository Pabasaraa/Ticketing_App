package com.csse.ticketing_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class DisplayPaymentActivity extends AppCompatActivity {

    DatabaseReference reference = FirebaseDatabase.getInstance( "https://ticketing-app-89a17-default-rtdb.asia-southeast1.firebasedatabase.app/" ).getReference ("users");

    TextView name, cardNum, expiryDate, cvv, mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_display_payment );

        Bundle bundle = getIntent().getExtras();

        name = findViewById(R.id.display_payment_name);
        cardNum = findViewById(R.id.display_payment_card_num);
        expiryDate = findViewById(R.id.display_payment_expiry_date);
        cvv = findViewById(R.id.display_payment_cvv);
        mobileNumber = findViewById(R.id.display_payment_mobile_num);

        Query checkUser = reference.child( bundle.getString( "username" )).child( "payment" );

        checkUser.addListenerForSingleValueEvent( new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists ( )) {
                    String nameFromDB = snapshot.child ( "cardHolderName" ).getValue( String.class );
                    String cardNumFromDB = snapshot.child ( "cardNum" ).getValue( String.class );
                    String expiryDateFromDB = snapshot.child ( "expiryDate" ).getValue( String.class );
                    String cvvFromDB = snapshot.child ( "cvv" ).getValue( String.class );
                    String mobileNumFromDB = snapshot.child ( "mobileNumber" ).getValue( String.class );

                    name.setText(nameFromDB);
                    cardNum.setText( cardNumFromDB );
                    expiryDate.setText( expiryDateFromDB );
                    cvv.setText( cvvFromDB );
                    mobileNumber.setText( mobileNumFromDB );
                } else {
                    name.setText ( "You will redirect~" );
                    cardNum.setVisibility( View.INVISIBLE );
                    expiryDate.setVisibility( View.INVISIBLE );
                    cvv.setVisibility( View.INVISIBLE );
                    mobileNumber.setVisibility( View.INVISIBLE );

                    Intent intent = new Intent( getApplicationContext(), AddPaymentActivity.class );
                    intent.putExtras( bundle );
                    startActivity( intent );
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}
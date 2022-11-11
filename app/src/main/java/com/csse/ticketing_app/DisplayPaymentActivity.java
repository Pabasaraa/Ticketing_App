package com.csse.ticketing_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DisplayPaymentActivity extends AppCompatActivity {

    DatabaseReference reference = FirebaseDatabase.getInstance( Constants.DB_INSTANCE ).getReference( Constants.DB_USER_REF );

    TextView name, cardNum, expiryDate, cvv, mobileNumber;
    AppCompatButton update, deleteBtn;
    ImageView backBtn;

    // Initialize logger
    public static final Logger log = Logger.getLogger( DisplayPaymentActivity.class.getName() );

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
        update = findViewById(R.id.update_displayPayment);
        backBtn = findViewById(R.id.back_btn_displayPayment);
        deleteBtn = findViewById(R.id.delete_payment_btn);

        Query checkUser = reference.child( bundle.getString( "username" )).child( Constants.DB_PAYMENT_REF );

        checkUser.addListenerForSingleValueEvent( new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists ( )) {
                    String nameFromDB = snapshot.child( Constants.DB_CHILD_CARD_HOLDER ).getValue( String.class );
                    String cardNumFromDB = snapshot.child( Constants.DB_CHILD_CARD_NUMBER ).getValue( String.class );
                    String expiryDateFromDB = snapshot.child( Constants.DB_CHILD_EXPIRY_DATE ).getValue( String.class );
                    String cvvFromDB = snapshot.child( Constants.DB_CHILD_CVV ).getValue( String.class );
                    String mobileNumFromDB = snapshot.child( Constants.DB_CHILD_MOBILE ).getValue( String.class );

                    name.setText(nameFromDB);
                    cardNum.setText( cardNumFromDB );
                    expiryDate.setText( expiryDateFromDB );
                    cvv.setText( cvvFromDB );
                    mobileNumber.setText( mobileNumFromDB );
                } else {
                    name.setText ( "You will redirect" );
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

        try {
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( getApplicationContext(), UpdatePaymentActivity.class );
                    intent.putExtras( bundle );
                    startActivity( intent );
                }
            });
        } catch (NullPointerException e) {
            // Logging thrown exception to the logger
            log.log( Level.SEVERE, e.getMessage());
        }

        try {
            deleteBtn.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reference.child( bundle.getString( "username" )).child( Constants.DB_PAYMENT_REF ).removeValue().addOnSuccessListener ( suc -> {
                        Toast.makeText ( DisplayPaymentActivity.this , "Deleted" , Toast.LENGTH_SHORT ).show ( );
                        Intent intent = new Intent ( getApplicationContext (), DashboardActivity.class );
                        startActivity ( intent );
                        finish ();
                    } );
                }
            });
        } catch (NullPointerException e) {
            // Logging thrown exception to the logger
            log.log( Level.SEVERE, e.getMessage());
        }

        try {
            backBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        } catch (NullPointerException e) {
            // Logging thrown exception to the logger
            log.log( Level.SEVERE, e.getMessage());
        }

    }
}
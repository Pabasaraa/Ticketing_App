package com.csse.ticketing_app;
// Constants Done
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UpdatePaymentActivity extends AppCompatActivity {

    ImageView backBtn;
    AppCompatEditText name, cardNum, expiryDate, cvv, mobileNum;
    AppCompatButton updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_update_payment );

        Bundle bundle = getIntent().getExtras();

        backBtn = findViewById( R.id.back_btn_updatePayment );
        name = findViewById( R.id.update_payment_name );
        cardNum = findViewById( R.id.update_payment_cardNumber );
        expiryDate = findViewById( R.id.update_payment_expiryDate );
        cvv = findViewById( R.id.update_payment_cvv );
        mobileNum = findViewById( R.id.update_payment_mobileNum );
        updateBtn = findViewById( R.id.payment_update_btn );

        DatabaseReference reference = FirebaseDatabase.getInstance( Constants.DB_INSTANCE ).getReference( Constants.DB_USER_REF );

        Query checkUser = reference.child( bundle.getString( Constants.BUNDLE_USERNAME )).child( Constants.DB_PAYMENT_REF );

        checkUser.addListenerForSingleValueEvent( new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists ( )) {

                    String nameFromDB = snapshot.child ( Constants.DB_CHILD_CARD_HOLDER ).getValue( String.class );
                    String cardNumFromDB = snapshot.child ( Constants.DB_CHILD_CARD_NUMBER ).getValue( String.class );
                    String expiryDateFromDB = snapshot.child ( Constants.DB_CHILD_EXPIRY_DATE ).getValue( String.class );
                    String cvvFromDB = snapshot.child ( Constants.DB_CHILD_CVV ).getValue( String.class );
                    String mobileNumFromDB = snapshot.child ( Constants.DB_CHILD_MOBILE ).getValue( String.class );

                    name.setText(nameFromDB);
                    cardNum.setText( cardNumFromDB );
                    expiryDate.setText( expiryDateFromDB );
                    cvv.setText( cvvFromDB );
                    mobileNum.setText( mobileNumFromDB );

                } else {
                    Intent intent = new Intent( getApplicationContext(), DashboardActivity.class );
                    startActivity( intent );
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<> ();

                map.put ( Constants.DB_CHILD_CARD_HOLDER, Objects.requireNonNull(name.getText()).toString() );
                map.put ( Constants.DB_CHILD_CARD_NUMBER, Objects.requireNonNull(cardNum.getText()).toString() );
                map.put ( Constants.DB_CHILD_EXPIRY_DATE, Objects.requireNonNull(expiryDate.getText()).toString() );
                map.put ( Constants.DB_CHILD_CVV, Objects.requireNonNull(cvv.getText()).toString() );
                map.put ( Constants.DB_CHILD_MOBILE, Objects.requireNonNull(mobileNum.getText()).toString() );

                reference.child(bundle.getString( Constants.BUNDLE_USERNAME )).child( Constants.DB_PAYMENT_REF ).updateChildren(map).addOnSuccessListener(suc -> {

                    Toast.makeText( UpdatePaymentActivity.this , "Details Updated" , Toast.LENGTH_SHORT ).show();

                    Intent intent = new Intent(getApplicationContext(), DisplayPaymentActivity.class);
                    startActivity( intent );
                    finish();

                }).addOnFailureListener(er -> {

                    Toast.makeText( UpdatePaymentActivity.this , "" + er.getMessage(), Toast.LENGTH_SHORT).show();

                });
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}
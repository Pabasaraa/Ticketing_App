package com.csse.ticketing_app;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PaymentHelperClass {

    private String cardHolderName, cardNum, expiryDate, cvv, mobileNumber;

    public PaymentHelperClass() {}

    public PaymentHelperClass(String cardHolderName , String cardNum , String expiryDate , String cvv , String mobileNumber) {
        this.cardHolderName = cardHolderName;
        this.cardNum = cardNum;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.mobileNumber = mobileNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public PaymentHelperClass getPaymentDetails(String username) {
        PaymentHelperClass helperClass = new PaymentHelperClass();
        DatabaseReference reference = FirebaseDatabase.getInstance( Constants.DB_INSTANCE ).getReference( Constants.DB_USER_REF );
        Query checkUser = reference.child( username ).child( Constants.DB_PAYMENT_REF );

        checkUser.addListenerForSingleValueEvent( new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists ( )) {
                    String nameFromDB = snapshot.child( Constants.DB_CHILD_CARD_HOLDER ).getValue( String.class );
                    String cardNumFromDB = snapshot.child( Constants.DB_CHILD_CARD_NUMBER ).getValue( String.class );
                    String expiryDateFromDB = snapshot.child( Constants.DB_CHILD_EXPIRY_DATE ).getValue( String.class );
                    String cvvFromDB = snapshot.child( Constants.DB_CHILD_CVV ).getValue( String.class );
                    String mobileNumFromDB = snapshot.child( Constants.DB_CHILD_MOBILE ).getValue( String.class );

                    helperClass.setCardHolderName ( nameFromDB );
                    helperClass.setCardNum ( cardNumFromDB );
                    helperClass.setExpiryDate ( expiryDateFromDB );
                    helperClass.setCvv ( cvvFromDB );
                    helperClass.setMobileNumber ( mobileNumFromDB );


                } else {
                    return;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
        return helperClass;
    }

    public void addPaymentDetails(PaymentHelperClass helperClass) {
        this.cardHolderName = helperClass.getCardHolderName();
        this.cardNum = helperClass.getCardNum();
        this.expiryDate = helperClass.getExpiryDate();
        this.cvv = helperClass.getCvv();
        this.mobileNumber = helperClass.getMobileNumber();

        addData(helperClass);
    }

    public void addData(PaymentHelperClass helperClass) {
        if (helperClass != null){
            return;
        }else {
            DatabaseReference reference = FirebaseDatabase.getInstance( Constants.DB_INSTANCE ).getReference( Constants.DB_PAYMENT_REF );
            reference.setValue( helperClass );
        }
    }

    public Boolean validateCardNumber ( String card ) {

        if (card.length () > 16){
            return false;
        } else if (card.isEmpty ()) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean validateCvv ( String cvv ) {

        if (cvv.length () > 3){
            return false;
        } else if (cvv.length () < 3) {
           return false;
        } else if (cvv.length () == 3) {
            return true;
        } else {
            return false;
        }
    }
}

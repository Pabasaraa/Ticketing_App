package com.csse.ticketing_app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

class PaymentUnitTest {

    @Test
    public void testAddPayment() {
        PaymentHelperClass expectPayment = new PaymentHelperClass ( "TestUser", "200032001240", "12/30", "018", "077953028");

        PaymentHelperClass actualPayment = new PaymentHelperClass();
        actualPayment.addPaymentDetails( expectPayment );

        PaymentHelperClass expectedPayment = new PaymentHelperClass().getPaymentDetails( "TestUser" );

        assertEquals( expectPayment.getCardHolderName(), actualPayment.getCardHolderName() );
        assertEquals( expectPayment.getCardNum(), actualPayment.getCardNum() );
        assertEquals( expectPayment.getMobileNumber(), actualPayment.getMobileNumber() );
        assertEquals( expectPayment.getCvv(), actualPayment.getCvv() );
        assertEquals( expectPayment.getExpiryDate(), actualPayment.getExpiryDate() );
    }

//    @Test(expected = IllegalStateException.class)
//    public void negativeTestCaseAddPayment() throws IOException, NullPointerException {
//        PaymentHelperClass expectedPayment = new PaymentHelperClass().getPaymentDetails( "TestUser" );
//
//        assertEquals( expectedPayment.getCardHolderName(), "TestUser" );
//    }

}

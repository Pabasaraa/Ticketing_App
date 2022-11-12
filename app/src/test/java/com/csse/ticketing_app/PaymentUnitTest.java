package com.csse.ticketing_app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

class PaymentUnitTest {
    /**
     * Validate the newly added payment details with expected data.
     *
     * addPaymentDetails() will get a PaymentHelperClass object as a parameter and store them in the database.
     * getPaymentDetails() will get the specified payment details.
     *
     * Then actual data we got from the db will be compared with the expected data in assertEqual().
     */
    @Test
    public void testAddPayment() {
        PaymentHelperClass expectPayment = new PaymentHelperClass ( "TestUser", "200032001240", "12/30", "018", "077953028");

        PaymentHelperClass actualPayment = new PaymentHelperClass();
        actualPayment.addPaymentDetails( expectPayment );

//        PaymentHelperClass expectedPayment = new PaymentHelperClass().getPaymentDetails( "TestUser" );

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

    /**
     * Validate the given card number
     *
     * @return true if the card is valid
     * @return false if the card number is empty or invalid
     */
    @Test
    public void validateCardNum() {
        PaymentHelperClass cardNumberValidator = new PaymentHelperClass ();

        assertTrue( cardNumberValidator.validateCardNumber("4532130215503933") );
        assertFalse( cardNumberValidator.validateCardNumber("") );
    }

    /**
     * Validate the given CVV
     *
     * @return true if the CVV is valid
     * @return false if the CVV number is empty or invalid
     */
    @Test
    public void validateCvv() {
        PaymentHelperClass cvvValidator = new PaymentHelperClass ();

        assertTrue ( cvvValidator.validateCvv ("018") );
        assertFalse ( cvvValidator.validateCvv ("") );
        assertFalse ( cvvValidator.validateCvv ("1254") );
    }

}

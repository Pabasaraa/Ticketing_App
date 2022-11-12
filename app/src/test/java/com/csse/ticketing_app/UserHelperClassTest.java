package com.csse.ticketing_app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserHelperClassTest {

    @Test
    void getUser() {
        UserHelperClass helperClass = new UserHelperClass();
        helperClass.setUserName ( "hi" );

        assertEquals( "hi", helperClass.getUserName() );
    }
}
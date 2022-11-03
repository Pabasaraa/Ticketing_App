package com.csse.ticketing_app;

import org.junit.Assert;
import org.junit.Test;

public class UserTestCase {

    @Test
    public void testAddUser() {
        String nameStr = "TestUser";
        String usernameStr = "testusername";
        String nicStr = "200032001240";
        String pwStr = "abcabc";
        String balanceStr = "1000.00";

        UserHelperClass actualUser = new UserHelperClass ( nameStr, usernameStr, nicStr, pwStr, balanceStr );
        SignupActivity signup = new SignupActivity ();
        signup.addUser( nameStr, usernameStr, nicStr, pwStr, balanceStr );

        UserHelperClass expectedUser = signup.getUser ( "testusername" );

        Assert.assertEquals( expectedUser.getFullName(), actualUser.getFullName() );
        Assert.assertEquals( expectedUser.getNic(), actualUser.getNic() );
        Assert.assertEquals( expectedUser.getMobileNum(), actualUser.getMobileNum() );
    }
}

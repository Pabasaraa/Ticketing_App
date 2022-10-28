package com.csse.ticketing_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText fullName, username, nic, password;
    AppCompatButton signupBtn;
    Button alreadyAMember;

    FirebaseDatabase database = FirebaseDatabase.getInstance ( "https://ticketing-app-89a17-default-rtdb.asia-southeast1.firebasedatabase.app/" );
    DatabaseReference reference = database.getReference ("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signup );

        fullName = findViewById( R.id.signup_full_name );
        username = findViewById( R.id.signup_username );
        nic = findViewById( R.id.signup_nic );
        password = findViewById( R.id.signup_pw );
        signupBtn = findViewById( R.id.signup_btn );
        alreadyAMember = findViewById( R.id.have_an_account );

        alreadyAMember.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext (), LoginActivity.class );
                startActivity( intent );
                finish();
            }
        });

        signupBtn.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateFullName() | !validateEmail() | !validateNic() | !validatePassword()){
                    return;
                } else {
                    String nameStr = fullName.getText().toString();
                    String usernameStr = username.getText().toString();
                    String nicStr = nic.getText().toString();
                    String pwStr = password.getText().toString();

                    //Initialize the default balance a new user get when register into the system
                    String balanceStr = "1000.00";

                    UserHelperClass helperClass = new UserHelperClass ( nameStr, usernameStr, nicStr, pwStr, balanceStr );

                    DatabaseReference keyRef = reference.child ( usernameStr );

                    keyRef.setValue ( helperClass ).addOnSuccessListener (suc -> {
                        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }).addOnFailureListener ( err -> {
                        Toast.makeText(getApplicationContext(), "" + err.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                }

            }
        });

    }

    private Boolean validateFullName () {
        String nameStr = fullName.getText().toString();

        if (nameStr.isEmpty ()) {
            fullName.setError ( "Fields cannot be empty" );
            return false;
        } else {
            fullName.setError ( null );
            return true;
        }
    }

    private Boolean validateEmail () {
        String emailStr = username.getText().toString();

        if (emailStr.isEmpty ()) {
            username.setError ( "Fields cannot be empty" );
            return false;
        } else {
            username.setError ( null );
            return true;
        }
    }

    private Boolean validateNic () {
        String nicStr = nic.getText().toString();

        if (nicStr.isEmpty ()) {
            nic.setError ( "Fields cannot be empty" );
            return false;
        } else {
            nic.setError ( null );
            return true;
        }
    }

    private Boolean validatePassword () {
        String pwStr = password.getText().toString();

        if (pwStr.isEmpty ()) {
            password.setError ( "Fields cannot be empty" );
            return false;
        } else {
            password.setError ( null );
            return true;
        }
    }

}

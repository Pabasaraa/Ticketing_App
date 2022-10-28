package com.csse.ticketing_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    EditText userName, pw;
    Button notAMember, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        userName = findViewById( R.id.login_username );
        pw = findViewById( R.id.login_pw );
        notAMember = findViewById( R.id.not_a_member_btn );
        login = findViewById( R.id.login_btn );

        notAMember.setOnClickListener( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext (), SignupActivity.class );
                startActivity( intent );
                finish();
            }
        });

        login.setOnClickListener( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                userLogin( view );
            }
        });
    }

    private Boolean validateEmail() {
        String emailStr = userName.getText().toString();

        if (emailStr.isEmpty()) {
            userName.setError("Field cannot be empty");
            return false;
        }else {
            userName.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String passwordStr = pw.getText().toString();

        if (passwordStr.isEmpty()) {
            pw.setError("Field cannot be empty");
            return false;
        }else {
            pw.setError(null);
            return true;
        }
    }

    private void userLogin( View view ) {
        if( !validateEmail() | !validatePassword() ) {
            return;
        } else { isUser(); }
    }

    private void isUser() {
        String userEnteredUsername, userEnteredPw;

        userEnteredUsername = userName.getText().toString();
        userEnteredPw = pw.getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance( "https://ticketing-app-89a17-default-rtdb.asia-southeast1.firebasedatabase.app/" ).getReference ("users");

        Query checkUser = reference.orderByChild( "userName" ).equalTo( userEnteredUsername );

        checkUser.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists ( )) {
                    userName.setError ( null );

                    String pwFromDB = snapshot.child( userEnteredUsername ).child ( "password" ).getValue ( String.class );

                    if (Objects.requireNonNull( pwFromDB ).equals ( userEnteredPw )) {
                        pw.setError ( null );

                        String nameFromDB = snapshot.child( userEnteredUsername ).child( "fullName" ).getValue( String.class );
                        String nicFromDB = snapshot.child( userEnteredUsername ).child( "nic" ).getValue( String.class );
                        String balanceFromDB = snapshot.child( userEnteredUsername ).child( "balance" ).getValue( String.class );

                        Bundle bundle = new Bundle ( );
                        bundle.putString( "username" , userEnteredUsername );
                        bundle.putString( "name" , nameFromDB );
                        bundle.putString( "nic" , nicFromDB );
                        bundle.putString( "password" , pwFromDB );
                        bundle.putString( "balance" , balanceFromDB );

                        Intent intent = new Intent( getApplicationContext() , HomeActivity.class );
                        intent.putExtras( bundle );
                        startActivity( intent );

                        Toast.makeText( LoginActivity.this , "Login Successful" , Toast.LENGTH_SHORT ).show();
                    }else {
                        pw.setError("Wrong password");
                        pw.requestFocus();
                    }
                }else {
                    userName.setError("Wrong username");
                    userName.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

    }
}
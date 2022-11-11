package com.csse.ticketing_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class UpdateUserActivity extends AppCompatActivity {
    AppCompatEditText username, fullName, nic, mobile;
    AppCompatButton updateBtn;

    DatabaseReference reference = FirebaseDatabase.getInstance( "https://ticketing-app-89a17-default-rtdb.asia-southeast1.firebasedatabase.app/" )
            .getReference( "users" );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_update_user );
        
        username = findViewById ( R.id.update_profile_username );
        fullName = findViewById ( R.id.update_profile_fullname );
        nic = findViewById ( R.id.update_profile_nic );
        mobile = findViewById ( R.id.update_profile_mobileNum );
        updateBtn = findViewById ( R.id.profile_update_btn );
        
        Bundle bundle = getIntent().getExtras();
        
        username.setText( bundle.getString( "username" ) );
        fullName.setText( bundle.getString( "name" ) );
        nic.setText( bundle.getString( "nic" ) );
        mobile.setText( "+94 779 342 389" );
        
        updateBtn.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> updatedData = new HashMap<> ();

                updatedData.put( "userName", Objects.requireNonNull( username.getText() ).toString() );
                updatedData.put( "fullName", Objects.requireNonNull( fullName.getText() ).toString() );
                updatedData.put( "nic", Objects.requireNonNull( nic.getText() ).toString() );

                bundle.putString ( "username", username.getText().toString() );
                bundle.putString ( "name", fullName.getText().toString() );
                bundle.putString ( "nic", nic.getText().toString() );

                reference.updateChildren( updatedData ).addOnSuccessListener( suc -> {
                    Toast.makeText ( UpdateUserActivity.this , "Updated" , Toast.LENGTH_SHORT ).show();
                    Intent intent = new Intent ( getApplicationContext (), ProfileActivity.class );
                    intent.putExtras ( bundle );
                    startActivity ( intent );
                    finish ();
                } );

            }
        } );
    }
}
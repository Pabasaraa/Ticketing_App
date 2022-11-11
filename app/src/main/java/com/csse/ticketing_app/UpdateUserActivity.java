package com.csse.ticketing_app;
// Constants Done
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
import java.util.logging.XMLFormatter;

public class UpdateUserActivity extends AppCompatActivity {
    AppCompatEditText username, fullName, nic, mobile;
    AppCompatButton updateBtn;

    DatabaseReference reference = FirebaseDatabase.getInstance( Constants.DB_INSTANCE ).getReference( Constants.DB_USER_REF );

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
        
        username.setText( bundle.getString( Constants.BUNDLE_USERNAME ) );
        fullName.setText( bundle.getString( Constants.BUNDLE_FULL_NAME ) );
        nic.setText( bundle.getString( Constants.BUNDLE_NIC ) );
        mobile.setText( "+94 779 342 389" );
        
        updateBtn.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> updatedData = new HashMap<> ();

                updatedData.put( Constants.DB_CHILD_USERNAME, Objects.requireNonNull( username.getText() ).toString() );
                updatedData.put( Constants.DB_CHILD_FULL_NAME , Objects.requireNonNull( fullName.getText() ).toString() );
                updatedData.put( Constants.DB_CHILD_NIC, Objects.requireNonNull( nic.getText() ).toString() );

                bundle.putString ( Constants.BUNDLE_USERNAME, username.getText().toString() );
                bundle.putString ( Constants.BUNDLE_FULL_NAME, fullName.getText().toString() );
                bundle.putString ( Constants.BUNDLE_FULL_NAME, nic.getText().toString() );

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
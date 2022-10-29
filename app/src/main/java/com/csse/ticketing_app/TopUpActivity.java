package com.csse.ticketing_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TopUpActivity extends AppCompatActivity {

    ImageView back;
    EditText topUPEt;
    LinearLayoutCompat topUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_top_up );

        Bundle bundle = getIntent().getExtras();

        String balance = bundle.getString( "balance" );

        float floatBal = Float.parseFloat( balance );

        back = findViewById( R.id.back_btn_top_up );
        topUpBtn = findViewById( R.id.topup_btn );
        topUPEt = findViewById( R.id.topup_edit_text );

        back.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onBackPressed ();
            }
        });

        topUpBtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String topUpAmount = topUPEt.getText().toString();

                if (Integer.parseInt(topUpAmount) < 100 || Integer.parseInt(topUpAmount) > 10000) {
                    topUPEt.setError("Invalid Amount");
                } else {
                    float newBalance = floatBal + Float.parseFloat(topUpAmount);

                    Map<String, Object> map = new HashMap<> ();
                    map.put("balance", String.valueOf(newBalance));

                    DatabaseReference reference = FirebaseDatabase.getInstance( "https://ticketing-app-89a17-default-rtdb.asia-southeast1.firebasedatabase.app/" ).getReference ("users");

                    Query checkUser = reference.child( bundle.getString( "username" )).child( "payment" );

                    checkUser.addListenerForSingleValueEvent( new ValueEventListener () {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists ( )) {

                                reference.child ( bundle.getString ( "username" ) ).updateChildren(map).addOnSuccessListener(suc -> {
                                    bundle.putString ( "balance", String.valueOf(newBalance) );
                                    topUPEt.setText( null );

                                    Toast.makeText( TopUpActivity.this , "Amount added successfully" , Toast.LENGTH_SHORT ).show();
                                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                                    intent.putExtras ( bundle );
                                    startActivity ( intent );
                                    finish();

                                }).addOnFailureListener(er ->
                                {
                                    Toast.makeText( TopUpActivity.this , "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                                });

                            } else {
                                Toast.makeText( TopUpActivity.this , "Add a card first" , Toast.LENGTH_LONG ).show();

                                Intent intent = new Intent( getApplicationContext(), AddPaymentActivity.class );
                                intent.putExtras( bundle );
                                startActivity( intent );
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}
                    });

                }
            }
        });
    }
}
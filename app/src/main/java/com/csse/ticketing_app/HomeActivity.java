package com.csse.ticketing_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    AppCompatButton dashboardBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_home );

        Bundle bundle = getIntent().getExtras();

        dashboardBtn = findViewById( R.id.dashboard_btn );

        dashboardBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent ( getApplicationContext (), DashboardActivity.class );
                intent.putExtras( bundle );
                startActivity( intent );
            }
        } );
    }
}
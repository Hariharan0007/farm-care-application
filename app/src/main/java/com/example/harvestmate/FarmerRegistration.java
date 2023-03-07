package com.example.harvestmate;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class FarmerRegistration extends AppCompatActivity{

    private Button register_farmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_registration);

        register_farmer = findViewById(R.id.farmer_register_btn);

        register_farmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FarmerRegistration.this,"Successfully registered!!!",Toast.LENGTH_SHORT).show();
                Intent login = new Intent(FarmerRegistration.this,MainActivity.class);
                startActivity(login);
                finish();
            }
        });


    }
}

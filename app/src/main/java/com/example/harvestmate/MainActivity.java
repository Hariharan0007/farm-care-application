package com.example.harvestmate;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView register_text;
    private Button login_farmer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register_text = findViewById(R.id.farmer_reg);
        login_farmer = findViewById(R.id.farmer_login_btn);

        register_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(MainActivity.this,FarmerRegistration.class);
                startActivity(reg);
                finish();
            }
        });

        login_farmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainActivity.this,HomeActivity.class);
                Toast.makeText(MainActivity.this,"Login successfull",Toast.LENGTH_SHORT).show();
                startActivity(login);
                finish();
            }
        });

    }
}
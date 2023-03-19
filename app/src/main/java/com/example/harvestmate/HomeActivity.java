package com.example.harvestmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private String f_name,f_num;

    ImageView book,bookings,records,help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        book = findViewById(R.id.book_drone);
        bookings = findViewById(R.id.bookings);
        records = findViewById(R.id.records);
        help = findViewById(R.id.help_support);

        Intent data = getIntent();
        f_name = data.getStringExtra("FName");
        f_num = data.getStringExtra("FNumber");

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bookdrone = new Intent(HomeActivity.this,BookDrone.class);
                bookdrone.putExtra("FNumber",f_num);
                bookdrone.putExtra("FName",f_name);
                Toast.makeText(HomeActivity.this,f_num.toString(),Toast.LENGTH_SHORT).show();
                startActivity(bookdrone);
            }
        });

        bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bookings = new Intent(HomeActivity.this,Bookings.class);
                bookings.putExtra("FName",f_name);
                bookings.putExtra("FNumber",f_num);
                startActivity(bookings);
            }
        });

        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rec = new Intent(HomeActivity.this,Records.class);
                startActivity(rec);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent help_support = new Intent(HomeActivity.this,HelpSupport.class);
                startActivity(help_support);
            }
        });
    }
}
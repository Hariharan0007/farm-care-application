package com.example.harvestmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class Bookings extends AppCompatActivity {

//    private RecyclerView booking_list = findViewById(R.id.bookings_card);

    private String f_name,f_num;

    private DatabaseReference booking_db = FirebaseDatabase.getInstance().getReference("FARMERS");

    private RecyclerView BookingsRV;
    private BookingAdapter bookingAdapter;
    private ArrayList<BookingModel> BookingModelArrayList;

    private String date,time,status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);


        Intent user_data = getIntent();
        f_name = user_data.getStringExtra("FName");
        f_num = user_data.getStringExtra("FNumber");

        ArrayList<String> bk_keys = new ArrayList<>();

        BookingsRV = findViewById(R.id.Bookings_card_RV);
        BookingsRV.setLayoutManager(new LinearLayoutManager(this));
        BookingModelArrayList = new ArrayList<>();
        bookingAdapter = new BookingAdapter(Bookings.this, BookingModelArrayList);
        BookingsRV.setAdapter(bookingAdapter);

        DatabaseReference bookingsRef = booking_db.child(f_num).child("BOOKINGS");

        bookingsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                BookingModelArrayList.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    bk_keys.add(ds.getKey());
                    Log.d("Booking Keys",ds.getKey());
                    Log.d("Booking values", Objects.requireNonNull(ds.getValue()).toString() + " " + ds.getChildren());

                    if (Objects.equals(ds.getKey(), "COUNT"))
                        continue;

                    date = ds.child("DATE").getValue().toString();
                    time = ds.child("TIME").getValue().toString();
                    status = ds.child("STATUS").getValue().toString();

                    BookingModelArrayList.add(new BookingModel(date,time,status));
                }
                bookingAdapter = new BookingAdapter(Bookings.this, BookingModelArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Booking Keys","Error");
            }
        });
    }
}
package com.example.harvestmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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
        loadArray(bk_keys,f_num);

        BookingsRV = findViewById(R.id.Bookings_card_RV);
        BookingsRV.setLayoutManager(new LinearLayoutManager(this));


        // Here, we have created new array list and added data to it
        BookingModelArrayList = new ArrayList<>();

        bookingAdapter = new BookingAdapter(Bookings.this,BookingModelArrayList);

        BookingsRV.setAdapter(bookingAdapter);



//        Toast.makeText(Bookings.this,Booking_keys.size(),Toast.LENGTH_SHORT).show();

//        booking_db.child(f_num).child("BOOKINGS").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot data : snapshot.getChildren()){
//                    Booking_keys.add(data.toString());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

//        bk_keys.add("BOOK1");
//        bk_keys.add("BOOK2");
//        bk_keys.add("BOOK3");
//        bk_keys.add("BOOK4");
//        bk_keys.add("BOOK5");
//
//        System.out.println(bk_keys);

        for(String keys : bk_keys){
            booking_db.child(f_num).child("BOOKINGS").child(keys).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        date = snapshot.child("DATE").getValue().toString();
                        time = snapshot.child("TIME").getValue().toString();
                        status = snapshot.child("STATUS").getValue().toString();
                        BookingModelArrayList.add(new BookingModel(date,time,status));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        bookingAdapter.notifyDataSetChanged();

        BookingModelArrayList.add(new BookingModel("22-01-2023", "CBE", "Waiting"));
        BookingModelArrayList.add(new BookingModel("23-01-2023", "CBE", "Waiting"));
        BookingModelArrayList.add(new BookingModel("24-01-2023", "CBE", "Waiting"));
        BookingModelArrayList.add(new BookingModel("25-01-2023", "CBE", "Waiting"));
        BookingModelArrayList.add(new BookingModel("26-01-2023", "CBE","Waiting"));
        BookingModelArrayList.add(new BookingModel("27-01-2023", "CBE","Waiting"));
        BookingModelArrayList.add(new BookingModel("28-01-2023", "CBE", "Waiting"));

//        System.out.println(BookingModelArrayList);





        // we are initializing our adapter class and passing our arraylist to it.
//        BookingAdapter bookingAdapter = new BookingAdapter(Bookings.this,BookingModelArrayList);



        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Bookings.this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
//        BookingsRV.setLayoutManager(linearLayoutManager);

//        try{
//            BookingsRV.setAdapter(bookingAdapter);
//        }
//        catch (Exception e){
//            Toast.makeText(Bookings.this,e.toString(),Toast.LENGTH_SHORT).show();
//            Intent fail = new Intent(Bookings.this,MainActivity.class);
//            startActivity(fail);
//        }

    }

    private ArrayList<String> loadArray(ArrayList<String> bk_keys, String f_num) {

        booking_db.child(f_num).child("BOOKINGS").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data : snapshot.getChildren()){
                    String keys = data.getKey();
                    if(!keys.equals("COUNT")) {
                        bk_keys.add(keys);
                        System.out.println(keys);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return bk_keys;

    }
}
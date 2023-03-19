package com.example.harvestmate;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class BookDrone extends AppCompatActivity {

    private DatabaseReference database = FirebaseDatabase.getInstance().getReference("FARMERS");

    private TextView date,time;
    private AutoCompleteTextView center;
    private Button bookdrone,bkdate,bktime;

    private com.google.android.material.textfield.TextInputLayout tf_booking_center;

    private String booking_date_input,booking_time_input,booking_center_input,f_name,f_num;

    private ArrayAdapter<String> center_adapter;

    int bk_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_drone);

        date = findViewById(R.id.drone_book_date);
        time = findViewById(R.id.drone_book_time);
        center = findViewById(R.id.drone_book_center);
        bookdrone = findViewById(R.id.book_drone_btn);

        tf_booking_center = findViewById(R.id.book_drone_center_layout);

        bkdate = findViewById(R.id.bookdate_select_btn);
        bktime = findViewById(R.id.booktime_select_btn);


        ArrayList<String> center_list = new ArrayList<>();

        center_list.add("CBE");
        center_list.add("TUP");
        center_list.add("ERD");


        center_adapter = new ArrayAdapter<String>(this, R.layout.dropdown, center_list);
        center.setAdapter(center_adapter);


        bkdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MaterialDatePicker<Long> materialdatepicker = MaterialDatePicker.Builder.datePicker().setTitleText("Select date").build();
                materialdatepicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        // Get the offset from our timezone and UTC.
                        TimeZone timeZoneUTC = TimeZone.getDefault();
                        // It will be negative, so that's the -1
                        int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;
                        // Create a date format, then a date object with our offset
                        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);  //yyyy-MM-dd
                        Date bdate = new Date(selection + offsetFromUTC);

                        date.setText(simpleFormat.format(bdate));
                    }
                });

                materialdatepicker.show(getSupportFragmentManager(),"TAG");
            }
        });

        bktime.setOnClickListener(new View.OnClickListener() {
            int hour,min;
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour = hourOfDay;
                        min = minute;
                        time.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,min));
                    }
                };
                TimePickerDialog timePickerDialog = new TimePickerDialog(BookDrone.this,onTimeSetListener,hour,min,false);
                timePickerDialog.setTitle("Choose Time");
                timePickerDialog.show();
            }
        });

        Intent user_data = getIntent();
        f_name = user_data.getStringExtra("FName");
        f_num = user_data.getStringExtra("FNumber");

        database.child(f_num).child("BOOKINGS").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                    bk_count = parseInt(snapshot.child("COUNT").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




//        database.child("FARMERS").child(f_num).child("BOOKINGS").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot snapshot1:snapshot.getChildren()){
//                    Toast.makeText(BookDrone.this, snapshot1.getKey().toString(),Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


        bookdrone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                booking_date_input = date.getText().toString();
                booking_time_input = time.getText().toString();
                booking_center_input = center.getText().toString();


                if(validateinput(booking_date_input,booking_time_input,booking_center_input)) {

//                    bk_count = (int) database.child("FARMERS").child(f_num).child("BOOKINGS").child("COUNT").get()

//                    bk_count++;

//                    database.child("FARMERS").child(f_num).child("BOOKINGS").child("COUNT").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<DataSnapshot> task) {
//                            bk_count = (int) task.getResult().getValue();
//                        }
//                    });


//                    database.child(f_num).child("BOOKINGS").addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            bk_count = (int) snapshot.getChildrenCount();
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
                    database.child(f_num).child("BOOKINGS").child("BOOK"+bk_count).child("DATE").setValue(booking_date_input);
                    database.child(f_num).child("BOOKINGS").child("BOOK"+bk_count).child("TIME").setValue(booking_time_input);
                    database.child(f_num).child("BOOKINGS").child("BOOK"+bk_count).child("CENTER").setValue(booking_center_input);
                    database.child(f_num).child("BOOKINGS").child("BOOK"+bk_count).child("STATUS").setValue("WAITING");
                    bk_count+=1;
                    database.child(f_num).child("BOOKINGS").child("COUNT").setValue(bk_count);

                    Toast.makeText(BookDrone.this, "Booking request sent", Toast.LENGTH_SHORT).show();

                    Intent book_suc = new Intent(BookDrone.this, HomeActivity.class);
                    book_suc.putExtra("FName",f_name);
                    book_suc.putExtra("FNumber",f_num);
                    startActivity(book_suc);
                    finish();
                }
            }

            private boolean validateinput(String b_date, String b_time, String b_center) {
                if(b_date.isEmpty()){
                    date.setError("Enter date");
                    date.requestFocus();
                    return false;
                }
                else if(b_time.isEmpty()){
                    time.setError("Enter Time");
                    time.requestFocus();
                    return false;
                }
                else if(b_center.isEmpty()){
                    center.setError("Choose Center");
                    center.requestFocus();
                    return false;
                }
                return true;
            }
        });




    }
}
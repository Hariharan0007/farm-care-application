package com.example.harvestmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference farmers_db = FirebaseDatabase.getInstance().getReference("FARMERS");

    private EditText f_name,f_num,f_pwsd;

    private String name,num,pwsd,t_name,t_pwsd;

    private TextView register_text;
    private Button login_farmer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register_text = findViewById(R.id.farmer_reg);
        login_farmer = findViewById(R.id.farmer_login_btn);

        f_name = findViewById(R.id.farmer_name);
        f_num = findViewById(R.id.farmer_number);
        f_pwsd  = findViewById(R.id.farmer_pwsd);

        register_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent reg = new Intent(MainActivity.this, FarmerRegistration.class);
                    startActivity(reg);
                    finish();
            }

        });

        login_farmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = f_name.getText().toString().trim().toUpperCase();
                num = f_num.getText().toString().trim();
                pwsd = f_pwsd.getText().toString().trim();

                if (validateInputs(name,num,pwsd)) {

                    farmers_db.child(num).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if(task.isSuccessful()) {
                                try {
                                    t_name = task.getResult().child("DETAILS").child("FARMER_NAME").getValue().toString();
                                    t_pwsd = task.getResult().child("DETAILS").child("FARMER_PWSD").getValue().toString();
                                    if (name.equals(t_name) && pwsd.equals(t_pwsd)) {
                                        Intent login = new Intent(getApplicationContext(), HomeActivity.class);
                                        login.putExtra("FName", t_name);
                                        login.putExtra("FNumber", num);

                                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                        startActivity(login);
                                        finish();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Invalid Data", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception e){
                                    Toast.makeText(MainActivity.this, "No Data Found For Your Mobile Number", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });

//                    farmers_db.child(num).addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            if(snapshot.exists()){
//                                String db_name = snapshot.child("DETAILS").child("FARMER_NAME").getValue().toString();
//                                String db_pwsd = snapshot.child("DETAILS").child("FARMER_PWSD").getValue().toString();
//                                if(name.equals(db_name)&&pwsd.equals(db_pwsd)){
//                                    Intent login = new Intent(getApplicationContext(), HomeActivity.class);
//
//                                    login.putExtra("FName",db_name);
//                                    login.putExtra("FNumber",num);
//
//                                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
//                                    startActivity(login);
//                                    finish();
//                                }
//                                else{
//                                    Toast.makeText(MainActivity.this, "Invalid Data", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                            else{
//                                Toast.makeText(MainActivity.this, "No Data Found For Your Mobile Number", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
                }


            }

            private boolean validateInputs(String name, String num, String pwsd) {
                if(name.isEmpty()){
                    f_name.setError("Enter name");
                    f_name.requestFocus();
                    return false;
                }
                else if(num.isEmpty()){
                    f_num.setError("Enter Mobile number");
                    f_num.requestFocus();
                    return false;
                }
                else if(pwsd.isEmpty()){
                    f_pwsd.setError("Enter Password");
                    f_pwsd.requestFocus();
                    return false;
                }
                return true;
            }
        });

    }
}
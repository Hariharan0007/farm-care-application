package com.example.harvestmate;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FarmerRegistration extends AppCompatActivity{

    private DatabaseReference farmer_db = FirebaseDatabase.getInstance().getReference().child("FARMERS");

    private Button register_farmer;

    private TextView reg_f_name,reg_mobile_no,reg_f_age,reg_f_add,reg_f_state,reg_f_pincode,reg_f_pwsd;

    private String name,pwsd,m_num,age,add,state,pincode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_registration);

        reg_f_name = findViewById(R.id.farmer_reg_name);
        reg_f_pwsd = findViewById(R.id.farmer_reg_password);
        reg_mobile_no = findViewById(R.id.farmer_reg_mobile);
        reg_f_age = findViewById(R.id.farmer_reg_age);
        reg_f_add = findViewById(R.id.farmer_reg_address);
        reg_f_state = findViewById(R.id.farmer_reg_state);
        reg_f_pincode = findViewById(R.id.farmer_reg_pincode);

        register_farmer = findViewById(R.id.farmer_register_btn);

        register_farmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                name = reg_f_name.getText().toString().trim().toUpperCase();
                pwsd = reg_f_pwsd.getText().toString().trim();
                m_num = reg_mobile_no.getText().toString().trim();
                age = reg_f_age.getText().toString().trim();
                add = reg_f_add.getText().toString().trim();
                state = reg_f_state.getText().toString().trim();
                pincode = reg_f_pincode.getText().toString().trim();


                if(ValidateInputs(name,pwsd,m_num,age,add,state,pincode)){
                    farmer_db.child(m_num).child("DETAILS").child("FARMER_NAME").setValue(name);
                    farmer_db.child(m_num).child("DETAILS").child("FARMER_PWSD").setValue(pwsd);
                    farmer_db.child(m_num).child("DETAILS").child("FARMER_AGE").setValue(age);
                    farmer_db.child(m_num).child("DETAILS").child("FARMER_ADD").setValue(add);
                    farmer_db.child(m_num).child("DETAILS").child("FARMER_STATE").setValue(state);
                    farmer_db.child(m_num).child("DETAILS").child("FARMER_PINCODE").setValue(pincode);
                    farmer_db.child(m_num).child("BOOKINGS").child("COUNT").setValue("0");
                    Toast.makeText(FarmerRegistration.this,"Successfully registered!!!",Toast.LENGTH_SHORT).show();
                    Intent login = new Intent(FarmerRegistration.this,MainActivity.class);
                    startActivity(login);
                    finish();
                }
            }

            private boolean ValidateInputs(String name, String pwsd, String m_num, String age, String add, String state, String pincode) {
                if(name.isEmpty())
                {
                    reg_f_name.setError("Enter name");
                    reg_f_name.requestFocus();
                    return false;
                }
                else if(pwsd.isEmpty())
                {
                    reg_f_pwsd.setError("Enter Password");
                    reg_f_pwsd.requestFocus();
                    return false;
                }
                else if(m_num.isEmpty())
                {
                    reg_mobile_no.setError("Enter name");
                    reg_mobile_no.requestFocus();
                    return false;
                }
                else if(age.isEmpty())
                {
                    reg_f_age.setError("Enter name");
                    reg_f_age.requestFocus();
                    return false;
                }
                else if(add.isEmpty())
                {
                    reg_f_add.setError("Enter name");
                    reg_f_add.requestFocus();
                    return false;
                }
                else if(state.isEmpty())
                {
                    reg_f_state.setError("Enter name");
                    reg_f_state.requestFocus();
                    return false;
                }
                else if(pincode.isEmpty())
                {
                    reg_f_pincode.setError("Enter name");
                    reg_f_pincode.requestFocus();
                    return false;
                }

                return true;
            }
        });


    }
}

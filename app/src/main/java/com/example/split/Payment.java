package com.example.split;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class Payment extends AppCompatActivity {


    Toolbar mToolbar;
    TextView textView18;
    TextInputLayout Title,date;
    Spinner payName,paidName;
    TextView textviewuseless;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        String type=getIntent().getStringExtra("name");

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Expense");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView18=(TextView)findViewById(R.id.textView18);
        date=(TextInputLayout) findViewById(R.id.date);
        Title=(TextInputLayout)findViewById(R.id.Title);
        textviewuseless=(TextView)findViewById(R.id.textviewuseless);

        paidName=(Spinner)findViewById(R.id.paidName);
        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paidName.setAdapter(spinnerAdapter);


        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference("groups");
        mDatabaseReference.keepSynced(true);
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                spinnerAdapter.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Upload upload=dataSnapshot1.getValue(Upload.class);
                    FirebaseAuth mFirebaseAuth=FirebaseAuth.getInstance();
                    if(dataSnapshot1.child("name").getValue().equals(Group.name) && dataSnapshot1.child("createdBy").getValue().equals(mFirebaseAuth.getCurrentUser().getEmail()))
                    {

                        for(int i=0;i<upload.getMembers().size();i++)
                        {
                            spinnerAdapter.add(upload.getMembers().get(i));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


        paidName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                payName=(Spinner)findViewById(R.id.payName);
                final ArrayAdapter<String> spinnerAdapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, android.R.id.text1);
                spinnerAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                payName.setAdapter(spinnerAdapter1);


                DatabaseReference mDatabaseReference1 = FirebaseDatabase.getInstance().getReference("groups");
                mDatabaseReference1.keepSynced(true);
                mDatabaseReference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        spinnerAdapter1.clear();
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                        {
                            Upload upload=dataSnapshot1.getValue(Upload.class);
                            FirebaseAuth mFirebaseAuth=FirebaseAuth.getInstance();
                            if(dataSnapshot1.child("name").getValue().equals(Group.name) && dataSnapshot1.child("createdBy").getValue().equals(mFirebaseAuth.getCurrentUser().getEmail()))
                            {

                                for(int i=0;i<upload.getMembers().size();i++)
                                {
                                    if(!(paidName.getSelectedItem().toString().equals(upload.getMembers().get(i))))
                                    {
                                        spinnerAdapter1.add(upload.getMembers().get(i));
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //spinnerAdapter.add("value");
        //spinnerAdapter.notifyDataSetChanged();

        if(type.equals("payment"))
        {
            textView18.setText("Add Your Payment!");
            Title.getEditText().setText("Payment");
            Title.getEditText().setEnabled(false);

            getSupportActionBar().setTitle("Payment");
        }

        date.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c =Calendar.getInstance();
                int year=c.get(Calendar.YEAR);
                int month=c.get(Calendar.MONTH);
                int day=c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog d= new DatePickerDialog(Payment.this, 0, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        c.set(Calendar.YEAR,year);
                        c.set(Calendar.MONTH,month);
                        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        String currentDateString= DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

                        date.getEditText().setText(currentDateString);
                    }
                },year,month,day);
                d.show();
            }
        });



    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

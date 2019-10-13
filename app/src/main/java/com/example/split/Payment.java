package com.example.split;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Objects;

public class Payment extends AppCompatActivity {


    Toolbar mToolbar;
    TextView textView18;
    TextInputLayout Title,date,amount;
    Spinner payName,paidName;
    Button save;
    ProgressDialog mPayment;
    DatabaseReference mDatabaseRef,mDR;
    Transaction trans;
    String PaidBy,PaidTo,Title1,date1;
    Double Amt;
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Payment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView18=(TextView)findViewById(R.id.textView18);
        textView18.setText("Add Your Payment!");
        date=(TextInputLayout) findViewById(R.id.date);
        Title=(TextInputLayout)findViewById(R.id.Title);
        Title.getEditText().setText("Payment");
        save=(Button)findViewById(R.id.save);
        amount=(TextInputLayout)findViewById(R.id.amount);
        final String name=getIntent().getStringExtra("name");
        mPayment= new ProgressDialog(this);

        paidName=(Spinner)findViewById(R.id.paidName);
        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paidName.setAdapter(spinnerAdapter);


        final DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference("groups");
        mDatabaseReference.keepSynced(true);
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                spinnerAdapter.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Upload upload=dataSnapshot1.getValue(Upload.class);
                    FirebaseAuth mFirebaseAuth=FirebaseAuth.getInstance();
                    for(int j=0;j<upload.getViewers().size();j++)
                    {
                        if(dataSnapshot1.child("name").getValue().equals(Group.name) && dataSnapshot1.child("viewers").child(String.valueOf(j)).getValue().equals(mFirebaseAuth.getCurrentUser().getEmail()))
                        {

                            for(int i=0;i<upload.getMembers().size();i++)
                            {
                                spinnerAdapter.add(upload.getMembers().get(i));
                            }
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
                            for(int j=0;j<upload.getViewers().size();j++)
                            {
                                if(dataSnapshot1.child("name").getValue().equals(Group.name) && dataSnapshot1.child("viewers").child(String.valueOf(j)).getValue().equals(mFirebaseAuth.getCurrentUser().getEmail()))
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


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Title1="Payment";
                PaidBy=paidName.getSelectedItem().toString();
                PaidTo=payName.getSelectedItem().toString();
                date1= date.getEditText().getText().toString();


                if(amount.getEditText().getText().toString().isEmpty())
                {
                    amount.setError("Enter the Amount");
                    amount.requestFocus();
                }
                else if(date1.isEmpty())
                {
                    date.setError("Enter the Date");
                    date.requestFocus();
                }
                else
                {
                    mPayment.setMessage("Processing");
                    mPayment.setCanceledOnTouchOutside(false);
                    mPayment.show();
                    Amt= Double.valueOf(amount.getEditText().getText().toString());
                    mDatabaseRef=FirebaseDatabase.getInstance().getReference("groups");
                    mDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                            {
                                Upload upload= dataSnapshot1.getValue(Upload.class);

                                if(upload.getName().equals(name) && upload.getViewers().contains((FirebaseAuth.getInstance().getCurrentUser()).getEmail().trim()))
                                {
                                    for(int i=0;i<upload.getMembers().size();i++)
                                    {
                                        if(upload.getMembers().get(i).equals(PaidBy))
                                        {
                                            Double netamt= upload.getNetAmt().get(i);
                                            netamt +=Amt;
                                            mDatabaseRef.child(dataSnapshot1.getKey()).child("netAmt").child(String.valueOf(i)).setValue(netamt);
                                        }
                                        if(upload.getMembers().get(i).equals(PaidTo))
                                        {
                                            Double netamt= upload.getNetAmt().get(i);
                                            netamt -=Amt;
                                            mDatabaseRef.child(dataSnapshot1.getKey()).child("netAmt").child(String.valueOf(i)).setValue(netamt);
                                        }
                                    }

                                    trans = new Transaction(Title1,PaidBy,PaidTo,date1,(Amt));
                                    mDR=FirebaseDatabase.getInstance().getReference("Transaction").child(((dataSnapshot1.getKey())));
                                    String key=mDR.push().getKey();
                                    mDR.child(key).setValue(trans).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            mPayment.dismiss();
                                            Intent paySucc=new Intent(getApplicationContext(),Group.class);
                                            paySucc.putExtra("Name",name);
                                            startActivity(paySucc);
                                        }
                                    })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    mPayment.hide();
                                                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
                                                }
                                            });
                                    break;

                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });


    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

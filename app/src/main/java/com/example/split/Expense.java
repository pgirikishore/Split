package com.example.split;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.util.Calendar;

public class Expense extends AppCompatActivity {

    Toolbar mToolbar;
    TextView textView18;
    TextInputLayout Title,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        String type=getIntent().getStringExtra("name");

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Expense");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView18=(TextView)findViewById(R.id.textView18);
        date=(TextInputLayout) findViewById(R.id.date);
        Title=(TextInputLayout)findViewById(R.id.Title);

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

                DatePickerDialog d= new DatePickerDialog(Expense.this, 0, new DatePickerDialog.OnDateSetListener() {
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

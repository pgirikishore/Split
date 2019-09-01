package com.example.split;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

   EditText editText2, editText3;
   Button button2;
   FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth= FirebaseAuth.getInstance();

        editText2=(EditText)findViewById(R.id.editText2);
        editText3=(EditText)findViewById(R.id.editText3);
        button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editText2.getText().toString();
                String password=editText3.getText().toString();
                if(email.isEmpty())
                {
                    editText2.setError("Please Enter Email Id");
                    editText2.requestFocus();
                }
                else if(password.isEmpty())
                {
                    editText3.setError("Please Enter password");
                    editText3.requestFocus();
                }
                else if(email.isEmpty() && password.isEmpty())
                {

                }
                else if(!(email.isEmpty() && password.isEmpty()))
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(Register.this, "Sign Up Unsuccessfull", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                startActivity(new Intent(Register.this, MainActivity.class));
                            }
                        }
                    });
                }
            }
        });

    }
}

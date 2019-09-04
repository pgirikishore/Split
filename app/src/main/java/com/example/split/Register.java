package com.example.split;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {

   EditText editText2, editText3,name;
   Button button2;
   FirebaseAuth mFirebaseAuth;
   DatabaseReference mDatabase;
   private ProgressDialog mRegProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth= FirebaseAuth.getInstance();
        mRegProgress =  new ProgressDialog(Register.this);

        editText2=(EditText)findViewById(R.id.editText2);
        editText3=(EditText)findViewById(R.id.editText3);
        name=(EditText)findViewById(R.id.name);
        button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editText2.getText().toString();
                String password=editText3.getText().toString();
                String name1= name.getText().toString();
                mRegProgress.setTitle("Registering User");
                mRegProgress.setMessage("Please wait while we create your account !");
                mRegProgress.setCanceledOnTouchOutside(false);
                mRegProgress.show();
                if(name1.isEmpty())
                {
                    mRegProgress.hide();
                    name.setError("Please Enter your Name");
                    name.requestFocus();
                }
                else if(email.isEmpty())
                {
                    mRegProgress.hide();
                    editText2.setError("Please Enter Email Id");
                    editText2.requestFocus();
                }
                else if(password.isEmpty())
                {
                    mRegProgress.hide();
                    editText3.setError("Please Enter password");
                    editText3.requestFocus();
                }
                else if(email.isEmpty() && password.isEmpty())
                {

                }
                else if(!(email.isEmpty() && password.isEmpty() && name1.isEmpty()))
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                mRegProgress.hide();
                                Toast.makeText(Register.this, "Sign Up Unsuccessfull", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                mRegProgress.dismiss();
                                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = current_user.getUid();

                                mDatabase= FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

                                HashMap<String, String> userMap= new HashMap<>();
                                userMap.put("name", name.getText().toString());
                                userMap.put("email", editText2.getText().toString());

                                mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                        {
                                            mFirebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(Register.this, "Registered Successfully. Please check your email for verification.",Toast.LENGTH_LONG).show();
                                                    Intent log= new Intent(Register.this,MainActivity.class);
                                                }
                                            });
                                        }
                                        else
                                        {
                                            Toast.makeText(Register.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                            }
                        }
                    });
                }
            }
        });


    }
}

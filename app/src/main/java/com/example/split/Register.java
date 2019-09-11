package com.example.split;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ToolbarWidgetWrapper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;

public class Register extends AppCompatActivity {

   TextInputLayout editText2, editText3,name,password1;
   Button button2;
   private androidx.appcompat.widget.Toolbar appid;
   FirebaseAuth mFirebaseAuth;
   DatabaseReference mDatabase;
   private ProgressDialog mRegProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        appid=findViewById(R.id.appid);
        setSupportActionBar(appid);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mFirebaseAuth= FirebaseAuth.getInstance();
        mRegProgress =  new ProgressDialog(Register.this);

        editText2=(TextInputLayout) findViewById(R.id.editText2);
        editText3=(TextInputLayout) findViewById(R.id.editText3);
        name=(TextInputLayout) findViewById(R.id.name);
        password1=(TextInputLayout)findViewById(R.id.password);
        button2=(Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editText2.getEditText().getText().toString();
                String password=editText3.getEditText().getText().toString();
                String name1= name.getEditText().getText().toString();
                String cpassword=password1.getEditText().getText().toString();
                mRegProgress.setTitle("Registering User");
                mRegProgress.setMessage("Please wait while we create your account !");
                mRegProgress.setCanceledOnTouchOutside(false);
                mRegProgress.show();
                if(password.equals(cpassword)) {
                    if (name1.isEmpty()) {
                        mRegProgress.hide();
                        name.getEditText().setError("Please Enter your Name");
                        name.getEditText().requestFocus();
                    } else if (email.isEmpty()) {
                        mRegProgress.hide();
                        editText2.getEditText().setError("Please Enter Email Id");
                        editText2.getEditText().requestFocus();
                    } else if (password.isEmpty()) {
                        mRegProgress.hide();
                        editText3.getEditText().setError("Please Enter password");
                        editText3.getEditText().requestFocus();
                    } else if (email.isEmpty() && password.isEmpty()) {

                    }
                    else if(cpassword.isEmpty())
                    {
                        mRegProgress.hide();
                        password1.getEditText().setError("Please Enter Email Id");
                        password1.getEditText().requestFocus();
                    }
                    else if (!(email.isEmpty() && password.isEmpty() && name1.isEmpty())) {
                        mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    mRegProgress.hide();
                                    Toast.makeText(Register.this, "Sign Up Unsuccessful", Toast.LENGTH_LONG).show();
                                } else {
                                    mRegProgress.dismiss();
                                    FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                    String uid = current_user.getUid();

                                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

                                    HashMap<String, String> userMap = new HashMap<>();
                                    userMap.put("name", name.getEditText().getText().toString());
                                    userMap.put("email", editText2.getEditText().getText().toString());

                                    mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                mFirebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(Register.this, "Registered Successfully. Please check your email for verification.", Toast.LENGTH_LONG).show();
                                                        Intent log = new Intent(Register.this, MainActivity.class);
                                                        startActivity(log);
                                                    }
                                                });
                                            } else {
                                                Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });

                                }
                            }
                        });
                    }
                }
                else
                {
                    if(cpassword.isEmpty())
                    {
                        mRegProgress.hide();
                        password1.getEditText().setError("Please Enter Email Id");
                        password1.getEditText().requestFocus();
                    }
                    else {
                        mRegProgress.dismiss();
                        Toast.makeText(Register.this, "Password Mismatch", Toast.LENGTH_LONG).show();
                    }
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

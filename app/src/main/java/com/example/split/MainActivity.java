package com.example.split;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView textView3;
    EditText editText, editText4;
    Button button;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView3=(TextView)findViewById(R.id.textView3);
        textView3.getPaint().setUnderlineText(true);

        mFirebaseAuth= FirebaseAuth.getInstance();

        editText=(EditText)findViewById(R.id.editText);
        editText4=(EditText)findViewById(R.id.editText4);
        button=(Button)findViewById(R.id.button);

        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser= mFirebaseAuth.getCurrentUser();
                if( mFirebaseUser != null)
                {
                    //Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
                    Intent intohome = new Intent(MainActivity.this, Home.class);
                    startActivity(intohome);
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid= user.getEmail();
                    Toast.makeText(MainActivity.this,uid,Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Please Login", Toast.LENGTH_LONG).show();
                }
            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editText.getText().toString();
                String password=editText4.getText().toString();
                if(email.isEmpty())
                {
                    editText.setError("Please Enter Email Id");
                    editText.requestFocus();
                }
                else if(password.isEmpty())
                {
                    editText4.setError("Please Enter password");
                    editText4.requestFocus();
                }
                else if(email.isEmpty() && password.isEmpty())
                {

                }
                else if(!(email.isEmpty() && password.isEmpty()))
                {
                    mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(MainActivity.this,"Login Error!",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                               Intent home = new Intent(MainActivity.this, Home.class);
                               startActivity(home);
                               //Toast.makeText(MainActivity.this,"Login Errorhi!",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }

    public void reg(View V)
    {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}

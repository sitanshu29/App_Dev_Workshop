package com.example.app_dev_workshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText Username, Password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Username = (EditText) findViewById(R.id.registeremail);
        Password = (EditText) findViewById(R.id.registerpwd);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.registerbtn).setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);
    }

    private void registerUser()
    {
        final String username = Username.getText().toString();
        final String password = Password.getText().toString();

        if (username.isEmpty())
        {
            Username.setError("Email is Required");
            Username.requestFocus();
            return;
        }

        if (password.isEmpty())
        {
            Password.setError("Password is Required");
            Password.requestFocus();
            return;
        }

        if (password.length() < 6)
        {
            Password.setError("Length should be more than 6");
            Password.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(SignUp.this, "User Registered Successufully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SignUp.this,Calculator.class);
                    intent.putExtra("name",username);
                    startActivity(intent);
                }
                else if(task.getException() instanceof FirebaseAuthUserCollisionException)
                {
                    Toast.makeText(SignUp.this,"You are already registered. Login now.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.register)
        {
            startActivity(new Intent(this, MainActivity.class));

        }
        else if(v.getId() == R.id.registerbtn)
        {
            registerUser();
        }

    }
}

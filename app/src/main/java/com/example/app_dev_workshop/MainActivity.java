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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText Username, Password;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = (EditText)findViewById(R.id.loginemail);
        Password = (EditText)findViewById(R.id.loginpwd);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.loginbtn).setOnClickListener(this);
        findViewById(R.id.signup).setOnClickListener(this);
    }

    private void userLogin()
    {
        final String username = Username.getText().toString();
        final String password = Password.getText().toString();

        if(username.isEmpty())
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

        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    Intent intent = new Intent(MainActivity.this, Calculator.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("name", username);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(MainActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.signup)
        {
            startActivity(new Intent(this, SignUp.class));

        }
        else if(v.getId() == R.id.loginbtn)
        {
            userLogin();
        }

    }
}

package com.example.relearn_iteration_one;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    Button btnLogIn, btnRegister;
    EditText email, password;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnLogIn = findViewById(R.id.btn_LogIn);
        email = findViewById(R.id.et_Email);
        password = findViewById(R.id.et_LoginPassword);
        btnLogIn = findViewById(R.id.btn_LogIn);
        auth = FirebaseAuth.getInstance();


        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                loginUser(txt_email,txt_password);

            }
        });
    }

    private void loginUser(String email, String password) {

        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(MainActivity2.this, "Log In Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
            }

        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity2.this, "No Account, Please Register", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity2.this, MainActivity3.class));
                        finish();
                    }
                });
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
        btnRegister = findViewById(R.id.btn_Register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
                finish();
            }
        });



        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
}
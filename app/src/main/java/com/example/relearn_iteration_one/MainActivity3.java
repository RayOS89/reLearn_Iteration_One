package com.example.relearn_iteration_one;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity3 extends AppCompatActivity {
    private EditText fullname, email, password;
    private Button btnRegisterBack, btnRegister2;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnRegisterBack  = findViewById(R.id.btn_RegisterBack);
        btnRegister2 = findViewById(R.id.btn_Register2);
        fullname = findViewById(R.id.etName2);
        email = findViewById(R.id.etEmail2);
        password = findViewById(R.id.et_Password);
        auth = FirebaseAuth.getInstance();

        btnRegisterBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);

            }
        });
        /*btnRegister2 = findViewById(R.id.btn_Register2);

        btnRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);

            }
        });*/
        btnRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_fullname = fullname.getText().toString();
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();


                if(TextUtils.isEmpty(txt_fullname) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(MainActivity3.this,"Empty Credentials!", Toast.LENGTH_SHORT).show();
                }else if (txt_password.length() < 6){
                    Toast.makeText(MainActivity3.this,"Password too short!", Toast.LENGTH_SHORT).show();
                }else{
                    registerUser(txt_fullname, txt_email, txt_password);
                }


            }
        });
    }

    private void registerUser(String fullname, String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity3.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity3.this, "Registered User Credentials Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity3.this, MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(MainActivity3.this,"User registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Toast.makeText(MainActivity3.this,"Empty Credentials", Toast.LENGTH_SHORT).show();
    }
}

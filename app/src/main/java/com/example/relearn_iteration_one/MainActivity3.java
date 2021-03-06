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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity3 extends AppCompatActivity {
    private EditText name, email, password;
    private Button btnRegisterBack, btnRegister2;
    private FirebaseAuth auth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference portfolioRef = db.collection("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnRegisterBack  = findViewById(R.id.btn_RegisterBack);
        btnRegister2 = findViewById(R.id.btn_Register2);
        name = findViewById(R.id.etName2);
        email = findViewById(R.id.etEmail2);
        password = findViewById(R.id.et_Password);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


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
                String txt_fullname = name.getText().toString();
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

    private void registerUser(String name, String email, String password) {// referenced from firebase authentication documentation https://firebase.google.com/docs/auth/android/email-link-auth?authuser=0
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity3.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity3.this, "Registered User Credentials Successfully", Toast.LENGTH_SHORT).show();
                    Register register = new Register(name, email, password);
                    portfolioRef.add(register);// adding auth entry to general "Users" Firestore DB
                    startActivity(new Intent(MainActivity3.this, DashboardActivity.class));
                    finish();
                }else
                    Toast.makeText(MainActivity3.this,"User registration Failed", Toast.LENGTH_SHORT).show();

            }

        });
    }

}

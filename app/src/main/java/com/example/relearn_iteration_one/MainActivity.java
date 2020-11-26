package com.example.relearn_iteration_one;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.Multiset;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String Key_Name = "name";
    private static final String Key_Email = "email";
    private static final String Key_Number = "number";

    private EditText etName, etEmail, etNumber;
    private TextView tvResult;
    private Button btnSave, btnUpdate, btnRetrieve, btnDelete;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference entryRef = db.collection("Class").document("Student Details");
    private ListenerRegistration EntryListener;
    private CollectionReference portfolioRef = db.collection("Class");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etNumber = findViewById(R.id.etNumber);
        tvResult = findViewById(R.id.tvResult);
        btnSave = findViewById(R.id.btnSave);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnRetrieve = findViewById(R.id.btnRetrieve);
        btnDelete = findViewById(R.id.btnDelete);



    btnSave.setOnClickListener(new View.OnClickListener() { // btnAdd in newest version would be more appropriate but didn't want to change yet as code is functioning as required.
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String number = etNumber.getText().toString();

                Entry entry = new Entry(name,email,number);
                portfolioRef.add(entry);
                /*Map<String, Object> Entry = new HashMap<>(); // Older variant of saving individual entries, updated to multiple entries facilitated.
                Entry.put(Key_Name, name);
                Entry.put(Key_Email, email);
                Entry.put(Key_Number, number);*/ //old hashmap version of data

                /*entryRef.set(entry)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                               // Log.d(TAG, e.toString());
                            }
                        });*/ // Older variant of saving individual entries, updated to multiple entries facilitated.
            }
        });
        btnRetrieve.setOnClickListener(new View.OnClickListener() { // retrieves multiple entries in newest version, may be  more appropriate to change naming convention, have not as code is functioning as required.
            @Override
            public void onClick(View v) {
                entryRef.get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()){
                                    Entry entry = documentSnapshot.toObject(Entry.class);
                                    /*String name = documentSnapshot.getString(Key_Name);
                                    String email = documentSnapshot.getString(Key_Email);
                                    String number  = documentSnapshot.getString(Key_Number);

                                    Map<String, Object> Entry = documentSnapshot.getData();*/ //old hasmap version

                                    String name = entry.getName();
                                    String email = entry.getEmail();
                                    String number = entry.getNumber();

                                    tvResult.setText("Name: " + name + "\n" + "Email: " + email + "\n" + "Number: " + number );
                                }else{
                                    Toast.makeText(MainActivity.this,"Entry does not exist",Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                               // Log.d(TAG, e.toString());
                            }
                        });
            }

        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String number = etNumber.getText().toString();

                Map<String, Object> Entry = new HashMap<>();
                Entry.put(Key_Name, name);
                Entry.put(Key_Email, email);
                Entry.put(Key_Number, number);
                //entryRef.update(Key_Email, email);
                entryRef.set(Entry, SetOptions.merge())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entryRef.delete()

               /* Map<String, Object> Entry = new HashMap<>();  This is one variation of deleting fields, can delete individual or multiple attributes.
                Entry.put(Key_Name, FieldValue.delete());
                Entry.put(Key_Email,FieldValue.delete());
                Entry.put(Key_Number,FieldValue.delete());
                entryRef.update(Entry) */
                //entryRef.update(Key_Email, FieldValue.delete()); alternate method to deletion, shorter but requires multiple versions if deleting individual attributes.
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        });
                        etName.setText("");
                        etEmail.setText("");
                        etNumber.setText("");


            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        EntryListener = entryRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Toast.makeText(MainActivity.this, "Error while loading",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (documentSnapshot.exists()){
                    Entry entry = documentSnapshot.toObject(Entry.class);

                    String name = entry.getName();
                    String email = entry.getEmail();
                    String number = entry.getNumber();

                    tvResult.setText("Name: " + name + "\n" + "Email: " + email + "\n" + "Number: " + number );
                } else {
                    tvResult.setText("");
                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        EntryListener.remove();
    }
}




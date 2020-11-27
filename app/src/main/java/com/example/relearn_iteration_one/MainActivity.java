package com.example.relearn_iteration_one;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String Key_Name = "name";
    private static final String Key_Email = "email";
    private static final String Key_Number = "number";
    private static final String TAG = "MainActivity";
    private EditText etName, etEmail, etNumber, etPriority;
    private TextView tvResult;
    private ScrollView svResult;
    private Button btnSave, btnUpdate, btnRetrieve, btnDelete, btnClear, btnNext;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference entryRef = db.collection("Class").document("Student Details");
    private ListenerRegistration EntryListener;
    private CollectionReference portfolioRef = db.collection("Class");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etPriority = findViewById(R.id.etPriority);
        etEmail = findViewById(R.id.etEmail);
        etNumber = findViewById(R.id.etNumber);
        tvResult = findViewById(R.id.tvResult);
        btnSave = findViewById(R.id.btnSave);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnRetrieve = findViewById(R.id.btnRetrieve);
        btnDelete = findViewById(R.id.btnDelete);
        btnClear = findViewById(R.id.btnClear);
        btnNext = findViewById(R.id.btnNext);



    btnSave.setOnClickListener(new View.OnClickListener() { // btnAdd in newest version would be more appropriate but didn't want to change yet as code is functioning as required.
            @Override
            public void onClick(View v) {
                if(etPriority.length()==0) {
                    etPriority.setText("0");
                }else{
                }
                int priority = Integer.parseInt(etPriority.getText().toString());

                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String number = etNumber.getText().toString();

                Entry entry = new Entry(name,email,number, priority);
                portfolioRef.add(entry);
            }
        });
        btnRetrieve.setOnClickListener(new View.OnClickListener() { // retrieves multiple entries in newest version, may be  more appropriate to change naming convention, have not as code is functioning as required.
            @Override
            public void onClick(View v) { // based off coding in flow youtube channel - https://www.youtube.com/playlist?list=PLrnPJCHvNZuDrSqu-dKdDi3Q6nM-VUyxD
                portfolioRef.whereGreaterThanOrEqualTo("priority", 4).orderBy("priority", Query.Direction.ASCENDING).limit(3)// querying based of priority id assigned to entries.
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                  @Override
                                                  public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                      String data = "";

                                                      for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                                         Entry entry = documentSnapshot.toObject(Entry.class);
                                                          entry.setID(documentSnapshot.getId());

                                                          String ID = entry.getID();
                                                          int priority = entry.getPriority();
                                                          String name = entry.getName();
                                                          String email = entry.getEmail();
                                                          String number = entry.getNumber();

                                                          data += "ID: " + ID + "\nPriority: " + priority + "\nName: " + name + "\n" + "Email: " + email + "\n" + "Number: " + number + "\n\n";
                                                      }
                                                      tvResult.setText(data);
                                                  }

                                              })

                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, e.toString());
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
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etPriority.setText("");
                etName.setText("");
                etEmail.setText("");
                etNumber.setText("");





            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity2();


            }
        });
    }
    public void openMainActivity2(){//code from lecture material on moving between activities
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
    @Override // Code referenced from coding in flow - https://www.youtube.com/playlist?list=PLrnPJCHvNZuDrSqu-dKdDi3Q6nM-VUyxD
    protected void onStart() {
        super.onStart();
        portfolioRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e!=null){
                    return;
                }
                String data = "";
                for (QueryDocumentSnapshot  documentSnapshot : queryDocumentSnapshots){
                    Entry entry = documentSnapshot.toObject(Entry.class);
                    entry.setID(documentSnapshot.getId());

                    String ID = entry.getID();
                    String name = entry.getName();
                    String email = entry.getEmail();
                    String number = entry.getNumber();
                    int priority = entry.getPriority();

                    data += "ID: " + ID + "\nPriority: " + priority + "\nName: " + name + "\n" + "Email: " + email + "\n" + "Number: " + number + "\n\n";
                }
                tvResult.setText(data);
                }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        EntryListener.remove();
    }
}




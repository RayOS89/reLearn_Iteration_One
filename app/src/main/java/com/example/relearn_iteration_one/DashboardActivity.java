package com.example.relearn_iteration_one;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;

import static com.example.relearn_iteration_one.R.string.firebase_database_url;

public class DashboardActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String Key_Spinner = "Spinner";
    private static final String Key_Question = "Question";
    private static final String Key_Answer = "Answer";
    private static final String Key_A = "A";
    private static final String Key_B = "B";
    private static final String Key_C = "c";
    private static final String Key_D = "D";
    private static final String TAG = "DashboardActivity";
    private EditText etquestion, etanswer, et_a, et_b, et_c, et_d,etSpinnerA;
    private Button btnSaveQ, btnUpdateQ, btnRetrieveQ, btnDeleteQ, btnClearQ, btnExitQ;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
   // private DocumentReference QuestionsEntry = db.collection("Subject").document();
    private DocumentReference portfolioRef = db.collection("Subject").document();
    private Spinner spinnerA;
    private RecyclerView recyclerView; // not implemented yet waiting to implement test app variant
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        etquestion = findViewById(R.id.et_question);
        etanswer = findViewById(R.id.et_answer);
        et_a = findViewById(R.id.et_a);
        et_b = findViewById(R.id.et_b);
        et_c = findViewById(R.id.et_c);
        et_d = findViewById(R.id.et_d);
        btnSaveQ = findViewById(R.id.btnSaveQ);
        btnUpdateQ = findViewById(R.id.btnUpdateQ);
        btnRetrieveQ = findViewById(R.id.btnRetrieveQ);
        btnDeleteQ = findViewById(R.id.btnDeleteQ);
        btnClearQ = findViewById(R.id.btnClearQ);
        btnExitQ = findViewById(R.id.btnExitQ);
        spinnerA = findViewById(R.id.spinner2);
        etSpinnerA = findViewById(R.id.etSpinnerA);
        recyclerView = findViewById(R.id.rvDashboard);
        textView = findViewById(R.id.textView);
        Query query = FirebaseFirestore.getInstance()
                .collection("Subjects");

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.subject_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerA.setAdapter(adapter);
        spinnerA.setOnItemSelectedListener(this);

        btnSaveQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String spinnerText = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), spinnerText, Toast.LENGTH_SHORT).show();
        etSpinnerA.setText(spinnerText);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void saveData() {
        if (etSpinnerA.getText().toString().equals("Subject Selection")) {
            Toast.makeText(DashboardActivity.this, "Please select a Subject", Toast.LENGTH_SHORT).show();
        } else {
            String spinnertext = etSpinnerA.getText().toString();
            String question = etquestion.getText().toString();
            String answer = etanswer.getText().toString();
            String a = et_a.getText().toString();
            String b = et_b.getText().toString();
            String c = et_c.getText().toString();
            String d = et_d.getText().toString();

            Questions questions = new Questions(spinnertext,question,answer,a,b,c,d);
            portfolioRef.set(questions);

        }


        btnRetrieveQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etSpinnerA.getText().toString().equals("Math")) {
                    portfolioRef.getFirestore().collection("Subject").document("Math");
                } else if (etSpinnerA.getText().toString().equals("English")) {
                    portfolioRef.getFirestore().collection("Subject").document("English");
                } /*else (etSpinnerA.getText().toString().equals("Irish")) {
                    portfolioRef.getFirestore().collection("Subject").document("Irish");
                }*/

                //textView.setText();
                /* recyclerView.setAdapter(new RecyclerView.Adapter() {
                    @NonNull
                    @Override
                    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        return null;
                    }

                    @Override
                    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

                    }

                    @Override
                    public int getItemCount() {
                        return 0;
                    }
                });*/
            }

        });
    }


}
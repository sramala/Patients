package com.kryptgames.patients;


import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;


import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import androidx.annotation.NonNull;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    public static final String PATIENT_NAME = "patientName";



    EditText patientName;
    EditText patientAge;
    Spinner spinner;
    Button addPatient;
    TextView textView;


    RecyclerView listViewPatients;
    ArrayList<Patient> patients;


    MyAdapter adapter;

    DatabaseReference databasePatients;


    LinearLayoutManager layoutManager;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        patientName = findViewById(R.id.patientName);
        patientAge = findViewById(R.id.patientAge);
        spinner = findViewById(R.id.spinner);
        addPatient = findViewById(R.id.addPatient);
        textView = findViewById(R.id.textView);

        listViewPatients = findViewById(R.id.listViewPatients);
        listViewPatients.setLayoutManager(new LinearLayoutManager(this));

        layoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);

        databasePatients = FirebaseDatabase.getInstance().getReference("patients");



        addPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPatient();
            }


        });


        Query myTopPostsQuery = FirebaseDatabase.getInstance().getReference("patients").orderByChild("patientName").equalTo(patientName.getText().toString());

        myTopPostsQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("Count ", "" + dataSnapshot.getChildrenCount());
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {


                    Patient patient = postSnapshot.getValue(Patient.class);
                    Log.e("Get Data", patient.getPatientName());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();


        databasePatients.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               patients = new ArrayList<Patient>();


                for (DataSnapshot patientSnapshot : dataSnapshot.getChildren()) {

                    Patient patient = patientSnapshot.getValue(Patient.class);
                    patients.add(patient);


                }

                adapter = new MyAdapter(MainActivity.this, patients);
                listViewPatients.setAdapter(adapter);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private void addPatient() {

        String name = patientName.getText().toString().trim();
        String genre = spinner.getSelectedItem().toString();

        if (!TextUtils.isEmpty(name)) {
            String key = databasePatients.push().getKey();
            String patientAgeStr = patientAge.getText().toString();
            Patient patient = new Patient(patientAgeStr, name, genre);

            assert patientAge != null;
            databasePatients.child(key).setValue(patient);

            patientName.setText("");


            Toast.makeText(this, "Patient Added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "You should enter a name ", Toast.LENGTH_LONG).show();
        }
    }

}






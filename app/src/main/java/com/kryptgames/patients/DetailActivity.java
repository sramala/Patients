package com.kryptgames.patients;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class DetailActivity extends AppCompatActivity {


    EditText  editTextBp, editTextSugar, editTextBlood, editTextDob, editTextDot;
    TextView patientName;
    Button updateDetail;



    DatabaseReference databaseDetails;


    RecyclerView detailView;
    ArrayList<Detail> details;

    DetailAdapter detailAdapter;

    LinearLayoutManager llm;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_patients);

       Intent intent = getIntent();


        databaseDetails = FirebaseDatabase.getInstance().getReference("Detail");

        patientName = findViewById(R.id.patientName);
        editTextBp = findViewById(R.id.editTextBp);
        editTextSugar = findViewById(R.id.editTextSugar);
        editTextBlood = findViewById(R.id.editTextBlood);
        editTextDob = findViewById(R.id.editTextDob);
        editTextDot = findViewById(R.id.editTextDot);
        updateDetail = findViewById(R.id.updateDetail);

        detailView = findViewById(R.id.detailView);
        detailView.setLayoutManager(new LinearLayoutManager(this));

        llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        patientName.setText(intent.getStringExtra(MainActivity.PATIENT_NAME));



        updateDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addDetail();


            }
        });
        Query myTopPostsQuery = FirebaseDatabase.getInstance().getReference("Detail").orderByChild("details").equalTo(patientName.getText().toString());

        myTopPostsQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("Count ", "" + dataSnapshot.getChildrenCount());
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {


                    Detail detail = postSnapshot.getValue(Detail.class);
                    Log.e("Get Data", detail.getPatientName());
                    Log.e("Get Data", detail.getPatientBlood());
                    Log.e("Get Data", detail.getPatientBp());
                    Log.e("Get Data", detail.getPatientDob());
                    Log.e("Get Data", detail.getPatientDot());
                    Log.e("Get Data", detail.getPatientSugar());

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

        databaseDetails.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                details = new ArrayList<Detail>();



                for (DataSnapshot detailSnapshot : dataSnapshot.getChildren()){

                    Detail detail = detailSnapshot.getValue(Detail.class);
                    details.add(detail);

                }

                detailAdapter = new DetailAdapter(DetailActivity.this, details);
                detailView.setAdapter(detailAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void addDetail(){

        String name = patientName.getText().toString().trim();

        if (!TextUtils.isEmpty(name)) {

            String key = databaseDetails.push().getKey();

            String patientBpStr = editTextBp.getText().toString().trim();
            String patientSugar = editTextSugar.getText().toString();
            String patientDob = editTextDob.getText().toString();
            String patientBlood = editTextBlood.getText().toString();
            String patientDot = editTextDot.getText().toString();

            Detail detail = new Detail(name, patientDob, patientSugar, patientBlood, patientDot, patientBpStr);


            assert  name != null;
            databaseDetails.child(key).setValue(detail);

            patientName.setText("");

            assert  patientBpStr != null;
            databaseDetails.child(key).setValue(detail);


            assert  patientBlood != null;
            databaseDetails.child(key).setValue(detail);


            assert  patientSugar != null;
            databaseDetails.child(key).setValue(detail);

            assert  patientDob != null;
            databaseDetails.child(key).setValue(detail);

            assert  patientDot!= null;
            databaseDetails.child(key).setValue(detail);







            Toast.makeText(this, "Updated Detail", Toast.LENGTH_LONG).show();



        } else {
            Toast.makeText(this, "No Field should be empty ", Toast.LENGTH_LONG).show();
        }
    }


}





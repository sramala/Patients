package com.kryptgames.patients;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    Context context;
    ArrayList<Detail> details;

    public DetailAdapter(Context c, ArrayList<Detail> p) {
        context = c;
        details = p;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.ViewHolder holder, int position) {

        holder.textViewPatientName.setText(details.get(position).getPatientName());
        holder.textViewBloodPressure.setText(details.get(position).getPatientBp());
        holder.textViewDiabetic.setText(details.get(position).getPatientSugar());
        holder.textViewHaemoglobin.setText(details.get(position).getPatientBlood());
        holder.textViewDateOfBirth.setText(details.get(position).getPatientDob());
        holder.textViewDateOfTest.setText(details.get(position).getPatientDot());



    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewPatientName, textViewBloodPressure, textViewDiabetic, textViewHaemoglobin, textViewDateOfBirth, textViewDateOfTest;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewPatientName = (TextView) itemView.findViewById(R.id.textViewPatientName);
            textViewBloodPressure = (TextView) itemView.findViewById(R.id.textViewBloodPressure);
            textViewDiabetic = (TextView) itemView.findViewById(R.id.textViewDiabetic);
            textViewHaemoglobin = (TextView) itemView.findViewById(R.id.textViewHaemoglobin);
            textViewDateOfBirth = (TextView) itemView.findViewById(R.id.textViewDateOfBirth);
            textViewDateOfTest = (TextView) itemView.findViewById(R.id.textViewDateOfTest);



        }
    }
}

package com.kryptgames.patients;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Patient> patients;

    public MyAdapter(Context c , ArrayList<Patient> p)
    {
        context = c;
        patients = p;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_patient,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        holder.textViewName.setText(patients.get(position).getPatientName());
        holder.textViewAge.setText(patients.get(position).getPatientAge());
        holder.textViewGenre.setText(patients.get(position).getPatientGenre());



    }




    @Override
    public int getItemCount() {
        return patients.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewAge, textViewGenre;

        public MyViewHolder(View itemView) {
            super(itemView);


            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewAge= (TextView) itemView.findViewById(R.id.textViewAge);
            textViewGenre = (TextView) itemView.findViewById(R.id.textViewGenre);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    context.startActivity(intent);


                }
            });



        }


    }
}

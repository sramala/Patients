package com.kryptgames.patients;

public class Patient {

    String patientAge;
    String patientName;
    String patientGenre;


    public Patient() {

    }

    public String getPatientAge() {
        return patientAge;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientGenre() {
        return patientGenre;
    }

    public Patient(String patientAge, String patientName, String patientGenre) {
        this.patientAge = patientAge;
        this.patientName = patientName;
        this.patientGenre = patientGenre;


    }
}



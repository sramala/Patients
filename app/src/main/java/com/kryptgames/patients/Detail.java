package com.kryptgames.patients;

public class Detail {



    String patientName;
    String patientBp;
    String patientSugar;
    String patientBlood;
    String patientDob;
    String patientDot;

    public Detail(){

    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setPatientBp(String patientBp) {
        this.patientBp = patientBp;
    }

    public void setPatientSugar(String patientSugar) {
        this.patientSugar = patientSugar;
    }

    public void setPatientBlood(String patientBlood) {
        this.patientBlood = patientBlood;
    }

    public void setPatientDob(String patientDob) {
        this.patientDob = patientDob;
    }

    public void setPatientDot(String patientDot) {
        this.patientDot = patientDot;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientBp() {
        return patientBp;
    }

    public String getPatientSugar() {
        return patientSugar;
    }

    public String getPatientBlood() {
        return patientBlood;
    }

    public String getPatientDob() {
        return patientDob;
    }

    public String getPatientDot() {
        return patientDot;
    }

    public Detail(String patientName, String patientBp, String patientSugar, String patientBlood, String patientDob, String patientDot) {
        this.patientName = patientName;
        this.patientBp = patientBp;
        this.patientSugar = patientSugar;
        this.patientBlood = patientBlood;
        this.patientDob = patientDob;
        this.patientDot = patientDot;


    }
}

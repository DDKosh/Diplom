package org.example.DTO;

import java.time.LocalDate;
import java.util.Date;

public class Appointment extends Abstract{

    private Patient patient;

    private Recipe recipe;

    private LocalDate visitDate;

    private String appeal;

    private String diagnosis;

    private Doctor doctor;

    /**
     * Instantiates a new Abstract.
     *
     * @param identifier the id
     */
    public Appointment(long identifier, Patient patient, Doctor doctor, java.sql.Date visitDate, String appeal, Recipe recipe, String diagnosis) {
        super(identifier);
        this.patient = patient;
        this.visitDate = visitDate.toLocalDate();
        this.appeal = appeal;
        this.recipe = recipe;
        this.diagnosis = diagnosis;
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public String getAppeal() {
        return appeal;
    }

    public void setAppeal(String appeal) {
        this.appeal = appeal;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}

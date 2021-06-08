package org.example.DTO;

import java.sql.Date;

/**
 * The type Recipe.
 */
public class Recipe extends Abstract {

    /**
     * @value recipe description
     */
    private String description;

    /**
     * @value patient
     */
    private Patient patient;

    /**
     * @value doctor
     */
    private Doctor doctor;

    /**
     * @value creation date of recipe
     */
    private Date creationDate;

    /**
     * @value recipe validity recipe
     */
    private int validity;

    /**
     * @value recipe priority
     */
    private RecipePriority priority;

    /**
     * Instantiates a new Recipe.
     *
     * @param id           the id
     * @param inputDescription  the description
     * @param inputPatient      the patient
     * @param inputDoctor       the doctor
     * @param inputCreationDate the creation date
     * @param inputValidity     the validity
     * @param inputPriority     the priority
     */
    public Recipe(final long id, final String inputDescription,
                  final Patient inputPatient,
                  final Doctor inputDoctor,
                  final Date inputCreationDate,
                  final int inputValidity,
                  final RecipePriority inputPriority) {
        super(id);
        this.description = inputDescription;
        this.patient = inputPatient;
        this.doctor = inputDoctor;
        this.creationDate = Date.valueOf(inputCreationDate.toLocalDate());
        this.validity = inputValidity;
        this.priority = inputPriority;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param inputDescription the description
     */
    public void setDescription(final String inputDescription) {
        this.description = inputDescription;
    }

    /**
     * Gets patient.
     *
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Gets doctor.
     *
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    public Date getCreationDate() {
        return new Date(creationDate.getTime());
    }

    /**
     * Gets validity.
     *
     * @return the validity
     */
    public int getValidity() {
        return validity;
    }

    /**
     * Gets priority.
     *
     * @return the priority
     */
    public RecipePriority getPriority() {
        return priority;
    }
}

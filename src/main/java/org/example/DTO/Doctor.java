package org.example.DTO;

import java.io.File;

/**
 * The type Doctor.
 */
public class Doctor extends Person {

    /**
     * @value specialization
     */
    private String specialization;
    private String courses;

    /**
     * Instantiates a new Doctor.
     *
     * @param id             the id
     * @param name           the name
     * @param patronymic     the patronymic
     * @param surname        the surname
     * @param inputSpecialization the specialization
     */
    public Doctor(final long id, final String name, final String patronymic,
                  final String surname, final String inputSpecialization,
                  final String courses) {
        super(id, name, patronymic, surname);
        this.specialization = inputSpecialization;
        this.courses = courses;
    }

    /**
     * Gets specialization.
     *
     * @return the specialization
     */
    public String getSpecialization() {
        return specialization;
    }
    public String getCourses() {
        return courses;
    }

}

package org.example.DTO;

/**
 * The type Patient.
 */
public class Patient extends Person {
    /**
     * @value phone number
     */
    private String phoneNumber;

    /**
     * Instantiates a new Patient.
     *
     * @param id          the id
     * @param name        the name
     * @param patronymic  the patronymic
     * @param surname     the surname
     * @param inputPhoneNumber the phone number
     */
    public Patient(final long id, final String name,
                   final String patronymic, final String surname,
                   final String inputPhoneNumber) {
        super(id, name, patronymic, surname);
        this.phoneNumber = inputPhoneNumber;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}


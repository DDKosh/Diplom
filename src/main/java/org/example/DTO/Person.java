package org.example.DTO;

/**
 * The type Person.
 */
public abstract class Person extends Abstract {

    /**
     * @value person name
     */
    private String name;

    /**
     * @value person patronymic
     */
    private String patronymic;

    /**
     * @value person surname
     */
    private String surname;

    /**
     * Instantiates a new Person.
     *
     * @param id         the id
     * @param inputName       the name
     * @param inputPatronymic the patronymic
     * @param inputSurname    the surname
     */
    public Person(final long id, final String inputName, final String inputPatronymic, final String inputSurname) {
        super(id);
        this.name = inputName;
        this.patronymic = inputPatronymic;
        this.surname = inputSurname;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param inputName the name
     */
    public void setName(final String inputName) {
        this.name = inputName;
    }

    /**
     * Gets patronymic.
     *
     * @return the patronymic
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }


    /**
     * Gets full name.
     *
     * @return the full name
     */
    public String getFullName() {
        return surname + " " + name + " " + patronymic;
    }
}

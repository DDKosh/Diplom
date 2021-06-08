package org.example.DTO;

/**
 * Recipe priority.
 */
public enum RecipePriority {
    /**
     * Normal recipe priority.
     */
    NORMAL("Нормальный"),
    /**
     * Cito recipe priority.
     */
    CITO("Срочный"),
    /**
     * Statim recipe priority.
     */
    STATIM("Немедленный");

    /**
     * @value identification recipe priority
     */
    private long id;

    /**
     * @value recipe priority
     */
    private String priority;

    /**
     * @param inputPriority the priority
     */
    RecipePriority(final String inputPriority) {
        this.priority = inputPriority;
        this.id = ordinal();
    }

    /**
     * Gets priority.
     *
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }
}

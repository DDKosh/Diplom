package org.example.DTO;

public class Specialization {
    private long id;

    private String name;

    public Specialization(final long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

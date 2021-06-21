package org.example.UI;

import com.vaadin.flow.component.grid.Grid;
import org.example.DAO.PatientDAO;
import org.example.DTO.Abstract;
import org.example.DTO.Patient;

/**
 * The type Patient ui.
 */
public class PatientUI extends PersonUI<Patient> {

    /**
     * Instantiates a new Patient ui.
     */

    public PatientUI(String role) {
        super(new PatientDAO(), role);
    }

    @Override
    public final void addColumnToGrid(final Grid<Patient> grid) {
        grid.addColumn(Patient::getPhoneNumber).setHeader("Номер телефона").setSortable(true);
    }
}

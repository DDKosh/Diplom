package org.example.UI;


import com.vaadin.flow.component.grid.Grid;
import org.example.DAO.DoctorDAO;
import org.example.DTO.Doctor;

/**
 * The type Doctor ui.
 */
public class DoctorUI extends PersonUI<Doctor> {

    /**
     * Instantiates a new Doctor ui.
     */
    public DoctorUI(String role) {
        super(new DoctorDAO(), role);
    }

    @Override
    public final void addColumnToGrid(final Grid<Doctor> grid) {
        //grid.addColumn(i -> new Image(Doctor::getPicFile)).setCaption("Picture");
        grid.addColumn(Doctor::getSpecialization).setHeader("Специализация").setSortable(true);
        grid.addColumn(Doctor::getCourses).setHeader("Курсы").setSortable(true);
    }
}

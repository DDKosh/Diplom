package org.example.UI;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.component.grid.GridSortOrderBuilder;
import org.example.DAO.AbstractDAO;
import org.example.DTO.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Person ui.
 *
 * @param <E> the type parameter
 */
public abstract class PersonUI<E extends Person> extends AbstractUI<E> {

    /**
     * Add column to grid.
     *
     * @param grid the grid
     */
    public abstract void addColumnToGrid(Grid<E> grid);

    /**
     * Instantiates a new Person ui.
     *
     * @param dao the dao
     */
    public PersonUI(final AbstractDAO<E> dao, String role) {
        super(dao, role);
    }

    @Override
    public final Grid<E> createGrid() {
        setEntity(new ArrayList<>());
        Grid<E> grid = new Grid<>();
        grid.asSingleSelect();
        grid.addColumn(E::getName).setHeader("Имя").setSortable(true);
        grid.addColumn(E::getPatronymic).setHeader("Отчество").setSortable(true);
        grid.addColumn(E::getSurname).setHeader("Фамилия").setSortable(true);
        addColumnToGrid(grid);
        List list = getDao().getAll();
        setEntity(list);
        grid.setItems(getEntity());
        grid.getDataProvider().refreshAll();

        return grid;
    }
}

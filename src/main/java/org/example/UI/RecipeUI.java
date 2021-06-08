package org.example.UI;

import com.vaadin.flow.component.grid.Grid;
import org.example.DAO.RecipeDAO;
import org.example.DTO.Recipe;

import java.util.List;

/**
 * The type Recipe ui.
 */
public class RecipeUI extends AbstractUI<Recipe> {
    /**
     * Instantiates a new Recipe ui.
     */
    public RecipeUI() {
        super(new RecipeDAO());
    }

    @Override
    public final Grid<Recipe> createGrid() {
        Grid<Recipe> grid = new Grid<>();
        grid.asSingleSelect();
        grid.addColumn(Recipe::getDescription).setHeader("Description");
        grid.addColumn(p -> p.getPatient().getFullName()).setHeader("Patient");
        grid.addColumn(d -> d.getDoctor().getFullName()).setHeader("Doctor");
        grid.addColumn(Recipe::getCreationDate).setHeader("Creation Date");
        grid.addColumn(Recipe::getValidity).setHeader("Validity");
        grid.addColumn(Recipe::getPriority).setHeader("Priority");
        setEntity((List<Recipe>) getDao().getAll());
        grid.getDataProvider().refreshAll();
        return grid;
    }
}

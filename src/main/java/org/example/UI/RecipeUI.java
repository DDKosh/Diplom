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
    public RecipeUI(String role) {
        super(new RecipeDAO(), role);
    }

    @Override
    public final Grid<Recipe> createGrid() {
        Grid<Recipe> grid = new Grid<>();
        grid.asSingleSelect();
        grid.addColumn(Recipe::getDescription).setHeader("Описание").setSortable(true);
        grid.addColumn(p -> p.getPatient().getFullName()).setHeader("Пациент").setSortable(true); //TODO
        grid.addColumn(d -> d.getDoctor().getFullName()).setHeader("Доктор").setSortable(true);
        grid.addColumn(Recipe::getCreationDate).setHeader("Дата создания").setSortable(true);
        grid.addColumn(Recipe::getValidity).setHeader("Срок действия").setSortable(true);
        grid.addColumn(Recipe::getPriority).setHeader("Приоритет").setSortable(true);
        setEntity((List<Recipe>) getDao().getAll());
        grid.getDataProvider().refreshAll();
        return grid;
    }
}

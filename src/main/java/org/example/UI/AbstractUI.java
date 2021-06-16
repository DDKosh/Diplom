package org.example.UI;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridSingleSelectionModel;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.component.grid.GridSortOrderBuilder;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLayout;
import org.example.DAO.AbstractDAO;
import org.example.DTO.Abstract;
import org.example.DTO.Person;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Abstract ui.
 *
 * @param <E> the type parameter
 */
public abstract class AbstractUI<E extends Abstract> extends VerticalLayout implements RouterLayout {
    /**
     * Create grid grid.
     *
     * @return the grid
     */
    public abstract Grid<E> createGrid();

    Div div = new Div();
    /**
     * The Dao.
     */
    private AbstractDAO<E> dao;
    /**
     * The Grid.
     */
    private Grid<E> grid;
    /**
     * The Entity.
     */
    private List<E> entity;

    /**
     * Instantiates a new Abstract ui.
     *
     * @param inputDao the dao
     */
    HorizontalLayout horizontalLayout = new HorizontalLayout();


    public AbstractUI(final AbstractDAO<E> inputDao) {
        /**
         * The Add button.
         */
        Button addButton = new Button("Добавить");
        div.add(addButton);
        addButton.addClickListener(e -> {
            EditAddModalUI<Abstract> editAddModal =
                    new EditAddModalUI<Abstract>(this);
            editAddModal.open();
            //getUI().addWindow(editAddModal);
        });

        /**
         * The Change button.
         */
        Button changeButton = new Button("Изменить");
        div.add(changeButton);
        changeButton.addClickListener(e -> {
            EditAddModalUI<Abstract> editAddModal =
                    new EditAddModalUI<Abstract>(this, getSelectedItem());
            editAddModal.open();
            Dialog dialog = new Dialog();
            dialog.setCloseOnOutsideClick(false);
            /*dialog.addDialogCloseActionListener(e -> {
                message.setText("Closed from server-side");
                dialog.close();
            });*/
            //getUI().addWindow(editAddModal);
        });

        /**
         * The Delete button.
         */
        Button deleteButton = new Button("Удалить");
        div.add(deleteButton);
        deleteButton.addClickListener(e -> {
            try {
                this.dao.delete(getSelectedItem().getId());
                refreshGrid();
            } catch (SQLIntegrityConstraintViolationException ex) {
                Notification.show("This object is used");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        this.dao = inputDao;
        /**
         * The Horizontal layout.
         */
        grid = createGrid();
        //grid.setVisible(true);
        /**
         * The Tab sheet.
         */
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(addButton, changeButton, deleteButton);
        add(horizontalLayout, grid);
        grid.setItems(entity);
        final Person[] o = new Person[1];
        grid.addItemClickListener(e -> {
            o[0] = (Person)grid.getSelectedItems().iterator().next();

        });
        grid.addItemDoubleClickListener(e -> {

            PersonInfo personInfo = new PersonInfo(o[0]);
            //this.setVisible(false);
            //grid.setVisible(false);
            //personInfo.setVisible(true);
            personInfo.open();
            //add(personInfo);
        });
        /*List<GridSortOrder<E>> sortByName = new GridSortOrderBuilder<E>()
                .thenDesc((Grid.Column<E>) grid.getColumns()).build();*/
        //grid.sort(sortByName);
        //grid.setSizeFull();
        //setVisible(true);
    }

    /**
     * Gets selected item.
     *
     * @return the selected item
     */
    public E getSelectedItem() {
        try {
            E selectEntity = grid.getSelectedItems().iterator().next();
            return selectEntity;
        } catch (NoSuchElementException e) {
            Notification.show(e.getMessage());
        }
        return null;
    }

    /**
     * Gets dao.
     *
     * @return the dao
     */
    public AbstractDAO<?> getDao() {
        return dao;
    }

    /**
     * Gets entity.
     *
     * @return the entity
     */
    public List<E> getEntity() {
        return entity;
    }

    /**
     * Set entity.
     *
     * @param selectEntity    the entity
     */
    public void setEntity(final List<E> selectEntity) {
        this.entity = selectEntity;
    }

    /**
     * Refresh grid.
     */
    public void refreshGrid() {
        entity.clear();
        entity.addAll(dao.getAll());
        grid.getDataProvider().refreshAll();
    }
}

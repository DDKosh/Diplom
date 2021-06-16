package org.example.UI;

import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import org.example.DAO.AppointmentDAO;
import org.example.DAO.DoctorDAO;
import org.example.DAO.RecipeDAO;
import org.example.DTO.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class AppointmentUI<E extends Abstract> extends Dialog {

    private ComboBox recipeBox = new ComboBox("Рецепт", new RecipeDAO().getAll());

    private DatePicker visitDate = new DatePicker("Дата приёма");

    private TextArea appeal = new TextArea("Жалоба");

    private TextArea diagnosis = new TextArea("Диагноз");

    private ComboBox doctorBox = new ComboBox("Доктор", new DoctorDAO().getAll());

    private Button okButton = new Button("Ок");

    private Button cancelButton = new Button("Отмена");

    AppointmentUI(Patient patient) {
        Div div = new Div();
        doctorBox.setItemLabelGenerator((ItemLabelGenerator<Doctor>) Doctor::getFullName);
        doctorBox.setWidth("300px");
        doctorBox.setRequired(true);

        recipeBox.setItemLabelGenerator((ItemLabelGenerator<Recipe>) Recipe::getDescription);
        recipeBox.setWidth("500px");
        recipeBox.setRequired(true);

        visitDate.setValue(LocalDate.now());

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(okButton, cancelButton);

        div.add(doctorBox, recipeBox, visitDate, appeal, diagnosis);

        okButton.addClickListener(e -> {
            Appointment appointment = new Appointment(new AppointmentDAO().getAllById(patient.getId()).size(),
                    patient,
                    (Doctor) doctorBox.getValue(),
                    Date.valueOf(visitDate.getValue()),
                    appeal.getValue(),
                    (Recipe) recipeBox.getValue(),
                    diagnosis.getValue()
                    );
            try {
                new AppointmentDAO().add(appointment);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            close();
        });

        cancelButton.addClickListener(e -> {
            close();
        });
    }

}

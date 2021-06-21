package org.example.UI;

import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.example.DAO.AppointmentDAO;
import org.example.DAO.DoctorDAO;
import org.example.DAO.RecipeDAO;
import org.example.DTO.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PersonInfo<E extends Abstract> extends Dialog {

    HorizontalLayout horizontalLayout;

    String role = null;

    PersonInfo(Person person, String role) {
        //List<Appointment> appointment = new AppointmentDAO().getAllById(person.getId());
        this.role = role;
        add(createDivInfo(person));
        add(createDivAppointment(person));
        VerticalLayout verticalLayout = new VerticalLayout();
        //verticalLayout.add(new TextField(appointment.get(0).getAppeal()), new Button("But"));
        add(verticalLayout);
    }

    private Div createDivInfo(Person person) {
        Div div = new Div();
        div.getStyle().set("border", "1px solid #008CFF").set("padding" , "15px");
        div.setText("Информация о пациенте");

        infoLayout((Patient) person, div);
        return div;
    }

    private void infoLayout(Patient person, Div div) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        TextField surnameField = new TextField("Фамилия");
        surnameField.setValue(person.getSurname());
        surnameField.setReadOnly(true);
        TextField patronymicField = new TextField("Отчество");
        patronymicField.setValue(person.getPatronymic());
        patronymicField.setReadOnly(true);
        TextField nameField = new TextField("Имя");
        nameField.setValue(person.getName());
        nameField.setReadOnly(true);
        horizontalLayout.add(surnameField, nameField, patronymicField);

        HorizontalLayout horizontalLayout1 = new HorizontalLayout();
        TextField policeField = new TextField("Номер полиса");
        policeField.setValue(String.valueOf(person.getPolicy()));
        policeField.setReadOnly(true);
        TextField registrationField = new TextField("Адрес прописки");
        registrationField.setValue(person.getRegistration());
        registrationField.setReadOnly(true);
        TextField phoneField = new TextField("Номер телефона");
        phoneField.setValue(person.getPhoneNumber());
        phoneField.setReadOnly(true);
        horizontalLayout1.add(policeField, registrationField, phoneField);

        div.add(horizontalLayout, horizontalLayout1);
    }

    private Div createDivAppointment(Person person) {
        Div div = new Div();
        div.getStyle().set("border", "1px solid #008CFF").set("padding" , "15px").set("margin-left", "auto");
        div.setText("История приёмов");
        if (role.equals("1")) {
            Button editButton = new Button("Добавить");
            editButton.getElement().getStyle().set("margin-left", "55px");
            div.add(editButton);
            editButton.addClickListener(e -> {
                Button saveButton = new Button("Сохранить");
                //saveButton.getElement().getStyle().set("margin-left", "55px");
                Button cancelButton = new Button("Отмена");
                cancelButton.getElement().getStyle().set("margin-left", "55px");

                //AppointmentUI appointmentUI = new AppointmentUI<Abstract>((Patient) person);
                AppointmentInfo appointmentUI = new AppointmentInfo();
                div.add(saveButton, cancelButton, appointmentUI);

                saveButton.addClickListener(w -> {
                    div.remove(cancelButton, saveButton);
                    try {
                        appointmentUI.saveAppointment((Patient) person);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    appointmentUI.setNonEditFields();
                });
                cancelButton.addClickListener(w -> {
                    div.remove(appointmentUI, cancelButton, saveButton);
                });
                //div.remove(horizontalLayout);
                //appointmentLayout((Patient)person, div);
            });
        }

        div.add();
        appointmentLayout((Patient) person, div);

        return div;
    }

    private void appointmentLayout(Patient patient, Div div) {
        List<Appointment> appointments = new AppointmentDAO().getAllById(patient.getId());
        for (Appointment appointment : appointments) {
            div.add(new AppointmentInfo(appointment, role), new Hr());
            /*horizontalLayout = new HorizontalLayout();
            horizontalLayout.add();*/
            /*TextField appeal = new TextField("Жалоба");
            appeal.setValue(appointment.getAppeal());
            TextField diagnose = new TextField("Диагноз");
            diagnose.setValue(appointment.getDiagnosis());
            DatePicker visitDate = new DatePicker("Дата приёма");
            visitDate.setValue(appointment.getVisitDate());
            horizontalLayout.add(appeal, diagnose, visitDate);*/
        }
        //div.add(horizontalLayout);
    }

    class AppointmentInfo extends HorizontalLayout{
        TextField appeal = new TextField("Жалоба");
        TextField diagnose = new TextField("Диагноз");
        DatePicker visitDate = new DatePicker("Дата приёма");
        TextField recipe = new TextField("Рецепт");
        TextField doctor = new TextField("Доктор");

        AppointmentInfo(Appointment appointment, String role) {
            appeal.setValue(appointment.getAppeal());
            appeal.setReadOnly(true);
            diagnose.setValue(appointment.getDiagnosis());
            diagnose.setReadOnly(true);
            visitDate.setValue(appointment.getVisitDate());
            visitDate.setReadOnly(true);
            recipe.setValue(appointment.getRecipe().getDescription());
            recipe.setReadOnly(true);
            doctor.setValue(appointment.getRecipe().getDoctor().getFullName());
            doctor.setReadOnly(true);

            add(appeal, diagnose, visitDate, recipe, doctor);
        }

        protected ComboBox doctorBox = new ComboBox("Доктор", new DoctorDAO().getAll());

        protected ComboBox recipeBox = new ComboBox("Рецепт", new RecipeDAO().getAll());

        AppointmentInfo() {
            appeal.setReadOnly(false);
            diagnose.setReadOnly(false);
            visitDate.setValue(LocalDate.now());
            visitDate.setReadOnly(false);
            recipe.setReadOnly(false);
            recipeBox.setItemLabelGenerator((ItemLabelGenerator<Recipe>) Recipe::getDescription);
            doctorBox.setItemLabelGenerator((ItemLabelGenerator<Doctor>) Doctor::getFullName);

            add(appeal, diagnose, visitDate, recipeBox, doctorBox);
        }

        public void setNonEditFields() {
            appeal.setReadOnly(true);
            diagnose.setReadOnly(true);
            visitDate.setReadOnly(true);
            recipeBox.setReadOnly(true);
            doctorBox.setReadOnly(true);
        }

        public void setEditFields() {
            appeal.setReadOnly(false);
            diagnose.setReadOnly(false);
            visitDate.setReadOnly(false);
            recipe.setReadOnly(false);
            doctor.setReadOnly(false);
        }

        public void saveAppointment(Patient patient) throws SQLException {
            Appointment appointment = new Appointment(new AppointmentDAO().getAll().size(),
                    patient,
                    (Doctor) doctorBox.getValue(),
                    Date.valueOf(visitDate.getValue()),
                    appeal.getValue(),
                    (Recipe) recipeBox.getValue(),
                    diagnose.getValue()
                    );
            new AppointmentDAO().add(appointment);
        }
    }
}

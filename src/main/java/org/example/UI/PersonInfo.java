package org.example.UI;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.example.DAO.AppointmentDAO;
import org.example.DAO.DoctorDAO;
import org.example.DAO.RecipeDAO;
import org.example.DTO.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PersonInfo<E extends Abstract> extends Dialog {

    HorizontalLayout horizontalLayout;

    PersonInfo(Person person) {
        //List<Appointment> appointment = new AppointmentDAO().getAllById(person.getId());
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
        //HorizontalLayout horizontalLayout = new HorizontalLayout();
        Button addButton = new Button("Сохранить");
        addButton.getElement().getStyle().set("margin-left", "55px");
        div.add(addButton);
        addButton.addClickListener(e -> {
            new AppointmentUI<Abstract>((Patient) person);
            div.remove(horizontalLayout);
            appointmentLayout((Patient)person, div);
        });
        //horizontalLayout.add(addButton);
        //div.add(horizontalLayout);

        div.add();
        appointmentLayout((Patient) person, div);

        return div;
    }

    private void appointmentLayout(Patient patient, Div div) {
        List<Appointment> appointments = new AppointmentDAO().getAllById(patient.getId());
        for (Appointment appointment : appointments) {
            div.add(new AppointmentInfo(appointment), new Hr());
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

    private void addAppointment(Patient patient, Div div) {

    }

    class AppointmentInfo extends HorizontalLayout{
        AppointmentInfo(Appointment appointment) {
            TextField appeal = new TextField("Жалоба");
            appeal.setValue(appointment.getAppeal());
            //appeal.setReadOnly(true);
            TextField diagnose = new TextField("Диагноз");
            diagnose.setValue(appointment.getDiagnosis());
            //diagnose.setReadOnly(true);
            DatePicker visitDate = new DatePicker("Дата приёма");
            visitDate.setValue(appointment.getVisitDate());
            //visitDate.setReadOnly(true);
            TextField recipe = new TextField("Рецепт");
            recipe.setValue(appointment.getRecipe().getDescription());
            //recipe.setReadOnly(true);
            TextField doctorBox = new TextField("Доктор");
            doctorBox.setValue(appointment.getRecipe().getDoctor().getFullName());
            //doctorBox.setReadOnly(true);

            add(appeal, diagnose, visitDate, recipe, doctorBox);
        }
    }
}

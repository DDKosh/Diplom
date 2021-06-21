package org.example.UI;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.example.DAO.UserDAO;
import org.example.DTO.Abstract;
import org.example.DTO.Patient;

import java.sql.SQLException;

public class AuthenticationUI extends Div {

    private TextField nameField = new TextField("Имя пользователя");

    private TextField passField = new TextField("Пароль");

    private Button loginButton = new Button("Войти");

    private VerticalLayout verticalLayout = new VerticalLayout();

    private boolean successAuth = false;

    public AuthenticationUI() {
    verticalLayout.add(nameField, passField, loginButton);
        loginButton.addClickListener(e -> {
            try {
                String role = new UserDAO().getByNameAndPass(nameField.getValue(), passField.getValue());
                if (role != null) {
                    successAuth = true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    add(verticalLayout);
    }

    public boolean getAuth() {
        return successAuth;
    }
}

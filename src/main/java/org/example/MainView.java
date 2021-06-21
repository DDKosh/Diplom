package org.example;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.router.Route;
import org.example.DAO.UserDAO;
import org.example.UI.AuthenticationUI;
import org.example.UI.DoctorUI;
import org.example.UI.PatientUI;
import org.example.UI.RecipeUI;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The main view contains a button and a click listener.
 */
@Route
@PWA(name = "My Application", shortName = "My Application")
public class MainView extends Div {

    public MainView() {
         TextField nameField = new TextField("Имя пользователя");

         PasswordField passField = new PasswordField("Пароль");

         Button loginButton = new Button("Войти");
         VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.add(nameField, passField, loginButton);
        //div.getElement().getStyle().set("margin-left","auto").set("margin-right","auto");
        //setHorizontalComponentAlignment(Alignment.CENTER, verticalLayout);
        //setAlignItems(Alignment.CENTER);
        Div div = new Div();
        div.add(verticalLayout);
        div.getElement().getStyle().set("margin-left", "43%").set("margin-top", "50px").set("margin-buttom", "50px");
        add(div);
        loginButton.addClickListener(e -> {
            try {
                if (nameField.getValue().trim().equals("") || passField.getValue().trim().equals("")) {
                    Notification.show("Укажите логин и пароль для входа");
                }
                String role = new UserDAO().getByNameAndPass(nameField.getValue(), passField.getValue());
                if (role != null) {
                    remove(div);
                    Tab tab1 = new Tab("Пациенты");
                    Div page1 = new Div();
                    page1.add(new PatientUI(role));

                    Tab tab2 = new Tab("Доктора");
                    Div page2 = new Div(new DoctorUI(role));
                    page2.setVisible(false);

                    Tab tab3 = new Tab("Рецепты");
                    Div page3 = new Div(new RecipeUI(role));
                    page3.setVisible(false);

                    Map<Tab, Component> tabsToPages = new HashMap<>();
                    tabsToPages.put(tab1, page1);
                    tabsToPages.put(tab2, page2);
                    tabsToPages.put(tab3, page3);
                    Tabs tabs = new Tabs(tab1, tab2, tab3);
                    tabs.setWidthFull();
                    Div pages = new Div(page1, page2, page3);
                    pages.setSizeFull();

                    tabs.addSelectedChangeListener(event -> {
                        tabsToPages.values().forEach(page -> page.setVisible(false));
                        Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
                        selectedPage.setVisible(true);
                    });

                    // Added this in here to make the `tabs` actually show on the page
                    add(tabs, pages);
                    setSizeFull();
                } else {
                    Notification.show("Ошибка авторизации");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });



        /*{
            remove(authenticationUI);
            Tab tab1 = new Tab("Пациенты");
            Div page1 = new Div();
            page1.add(new PatientUI());

            Tab tab2 = new Tab("Доктора");
            Div page2 = new Div(new DoctorUI());
            page2.setVisible(false);

            Tab tab3 = new Tab("Рецепты");
            Div page3 = new Div(new RecipeUI());
            page3.setVisible(false);

            Map<Tab, Component> tabsToPages = new HashMap<>();
            tabsToPages.put(tab1, page1);
            tabsToPages.put(tab2, page2);
            tabsToPages.put(tab3, page3);
            Tabs tabs = new Tabs(tab1, tab2, tab3);
            tabs.setWidthFull();
            Div pages = new Div(page1, page2, page3);
            pages.setSizeFull();

            tabs.addSelectedChangeListener(event -> {
                tabsToPages.values().forEach(page -> page.setVisible(false));
                Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
                selectedPage.setVisible(true);
            });

            // Added this in here to make the `tabs` actually show on the page
            add(tabs, pages);
            setSizeFull();
        }*/
    }
}

package org.example;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.router.Route;
import org.example.UI.DoctorUI;
import org.example.UI.PatientUI;
import org.example.UI.RecipeUI;

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
public class MainView extends VerticalLayout {

    public MainView() {

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
    }
}

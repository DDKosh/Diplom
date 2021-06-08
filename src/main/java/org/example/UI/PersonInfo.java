package org.example.UI;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class PersonInfo extends Dialog {
    private Div div;

    PersonInfo() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(new TextField(), new Button("Smth"));
        add(verticalLayout);
        add(new TextField());
        add(new Button("Smth"));
    }
}

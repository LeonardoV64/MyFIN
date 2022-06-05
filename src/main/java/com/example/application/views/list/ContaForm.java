package com.example.application.views.list;

import com.example.application.data.entity.Status;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class ContaForm extends FormLayout {
    TextField conta = new TextField("Conta");
    BigDecimalField saldo = new BigDecimalField("Saldo");
    ComboBox<Status> status = new ComboBox<Status>("Status");

    Button criar = new Button("Criar conta");
    Button deletar = new Button("Deletar conta");
    Button cancelar = new Button("Cancelar");

    public ContaForm(List<Status> statuses){
        addClassName("contact-form");

        status.setItems(statuses);
        status.setItemLabelGenerator(Status::getName);

        add(
                conta,
                saldo,
                status,
                createButtonLayout()
        );
    }

    private Component createButtonLayout(){
        criar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deletar.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancelar.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        criar.addClickShortcut(Key.ENTER);
        cancelar.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(criar, deletar, cancelar);
    }
}

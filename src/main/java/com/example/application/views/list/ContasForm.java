package com.example.application.views.list;


import java.util.List;

import com.example.application.data.entity.Contas;
import com.example.application.data.entity.Status;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

public class ContasForm extends FormLayout {
	private Contas contas;
	
    TextField conta = new TextField("Conta");
    BigDecimalField saldo = new BigDecimalField("Saldo");
    ComboBox<Status> status = new ComboBox<Status>("Status");
    Binder<Contas> binder = new BeanValidationBinder<>(Contas.class);

    Button criar = new Button("Criar");
    Button deletar = new Button("Deletar");
    Button cancelar = new Button("Cancelar");

    public ContasForm(List<Status> statuses){
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
    
    public void setConta(Contas contas) {
    	
        this.contas = contas;
        binder.readBean(contas);
      }
}

/*private static abstract class ContasFormEvent extends ComponentEvent<ContasForm> {
    private Contas contas;

    protected ContasFormEvent(ContasForm source, Contas contas) {
      super(source, false);
      this.contas = contas;
    }

    public Contas getConta() {
      return contas;
    }
  }

  public static class SaveEvent extends ContasFormEvent {
    SaveEvent(ContasForm source, Contas contact) {
      super(source, contas);
    }
  }

  public static class DeleteEvent extends ContasFormEvent {
    DeleteEvent(ContasForm source, Contas contact) {
      super(source, contas);
    }

  }

  public static class CloseEvent extends ContasFormEvent {
    CloseEvent(ContasForm source) {
      super(source, null);
    }
  }
  */




  









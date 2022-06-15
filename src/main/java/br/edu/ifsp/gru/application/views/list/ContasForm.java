package br.edu.ifsp.gru.application.views.list;

import br.edu.ifsp.gru.application.data.entity.Contas;
import br.edu.ifsp.gru.application.data.entity.Tipo;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class ContasForm extends FormLayout {
    Binder<Contas> binder = new BeanValidationBinder<>(Contas.class);


    TextField conta = new TextField("Conta");
    NumberField saldo = new NumberField("Saldo");
    ComboBox<Tipo> status = new ComboBox<Tipo>("Status");



    Button criar = new Button("Criar");
    Button deletar = new Button("Deletar");
    Button cancelar = new Button("Cancelar");
    private Contas contas;

    public ContasForm(List<Tipo> tipos){
        addClassName("contact-form");
        binder.bindInstanceFields(this);

        status.setItems(tipos);
        status.setItemLabelGenerator(Tipo::getName);

        add(
                conta,
                saldo,
                status,
                createButtonLayout()
        );
    }

    public void setConta(Contas contas){
        this.contas = contas;
        binder.readBean(contas);
    }

    private Component createButtonLayout(){
        criar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deletar.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancelar.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        criar.addClickListener(event -> validarESalvar());
        deletar.addClickListener(event -> fireEvent(new DeletarEvento(this, contas)));
        cancelar.addClickListener(event -> fireEvent(new FecharEvento(this)));

        criar.addClickShortcut(Key.ENTER);
        cancelar.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(criar, deletar, cancelar);
    }

    private void validarESalvar() {
        try{
            binder.writeBean(contas);
            fireEvent(new SalvarEvento(this, contas));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Eventos
    public static abstract class ContaFormEvento extends ComponentEvent<ContasForm> {
        private Contas contas;

        protected ContaFormEvento(ContasForm source, Contas contas) {
            super(source, false);
            this.contas = contas;
        }

        public Contas getContas() {
            return contas;
        }
    }

    public static class SalvarEvento extends ContaFormEvento {
        SalvarEvento(ContasForm source, Contas contas) {
            super(source, contas);
        }
    }

    public static class DeletarEvento extends ContaFormEvento {
        DeletarEvento(ContasForm source, Contas contas) {
            super(source, contas);
        }

    }

    public static class FecharEvento extends ContaFormEvento {
        FecharEvento(ContasForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}






  









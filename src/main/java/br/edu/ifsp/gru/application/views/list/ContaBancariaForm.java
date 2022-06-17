package br.edu.ifsp.gru.application.views.list;

import br.edu.ifsp.gru.application.data.entity.ContaBancaria;
import br.edu.ifsp.gru.application.data.entity.TipoConta;
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

public class ContaBancariaForm extends FormLayout {
    Binder<ContaBancaria> binder = new BeanValidationBinder<>(ContaBancaria.class);

    //Criação dos campos do formulário de criação, exclusão e atualização
    TextField conta = new TextField("Conta");
    NumberField saldo = new NumberField("Saldo");
    ComboBox<TipoConta> status = new ComboBox<TipoConta>("Tipo");


    //Criação dos botões
    Button criar = new Button("Criar");
    Button deletar = new Button("Deletar");
    Button cancelar = new Button("Cancelar");
    private ContaBancaria contaBancaria;

    //Cria o layout do formulário
    public ContaBancariaForm(List<TipoConta> tipoContas){
        addClassName("contact-form");
        binder.bindInstanceFields(this);

        status.setItems(tipoContas);
        status.setItemLabelGenerator(TipoConta::getName);

        add(
                conta,
                saldo,
                status,
                createButtonLayout()
        );
    }

    public void setConta(ContaBancaria contaBancaria){
        this.contaBancaria = contaBancaria;
        binder.readBean(contaBancaria);
    }

    //Molda os botões do formulário CRUD de contas
    private Component createButtonLayout(){
        criar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deletar.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancelar.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        criar.addClickListener(event -> validarESalvar());
        deletar.addClickListener(event -> fireEvent(new DeletarEvento(this, contaBancaria)));
        cancelar.addClickListener(event -> fireEvent(new FecharEvento(this)));

        criar.addClickShortcut(Key.ENTER);
        cancelar.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(criar, deletar, cancelar);
    }

    //Faz a validação e logo após salva a conta
    private void validarESalvar() {
        try{
            binder.writeBean(contaBancaria);
            fireEvent(new SalvarEvento(this, contaBancaria));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Eventos
    public static abstract class ContaFormEvento extends ComponentEvent<ContaBancariaForm> {
        private ContaBancaria contaBancaria;

        protected ContaFormEvento(ContaBancariaForm source, ContaBancaria contaBancaria) {
            super(source, false);
            this.contaBancaria = contaBancaria;
        }

        public ContaBancaria getContas() {
            return contaBancaria;
        }
    }

    public static class SalvarEvento extends ContaFormEvento {
        SalvarEvento(ContaBancariaForm source, ContaBancaria contaBancaria) {
            super(source, contaBancaria);
        }
    }

    public static class DeletarEvento extends ContaFormEvento {
        DeletarEvento(ContaBancariaForm source, ContaBancaria contaBancaria) {
            super(source, contaBancaria);
        }

    }

    public static class FecharEvento extends ContaFormEvento {
        FecharEvento(ContaBancariaForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}






  









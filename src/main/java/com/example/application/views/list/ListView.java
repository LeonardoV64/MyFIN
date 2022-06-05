package com.example.application.views.list;

import com.example.application.data.entity.Contas;
import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;

@Route(value = "")
@PageTitle("Contas Correntes")
public class ListView extends VerticalLayout {
    Grid<Contas> grid = new Grid<>(Contas.class);
    TextField filterText = new TextField();
    ContaForm form;
    private CrmService service;


    public ListView(CrmService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();

        configureGrid();
        configureForm();

        add(
                getToolbar(),
                getContent()
        );

        updateList();

    }

    private void updateList() {
        grid.setItems(service.buscaTodasContas(filterText.getValue()));
    }

    private Component getContent() {
        HorizontalLayout conteudo = new HorizontalLayout(grid, form);
        conteudo.setFlexGrow(2, grid);
        conteudo.setFlexGrow(1, form);
        conteudo.addClassName("conteudo");
        conteudo.setSizeFull();

        return conteudo;
    }

    private void configureForm() {
        form = new ContaForm(service.buscaTodosStatus());
        form.setWidth("25em");
    }

    private void configureGrid() {
        grid.addClassNames("contas-grid");
        grid.setSizeFull();
        grid.setColumns("conta", "saldo");
        grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Procurar por conta...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContaButton = new Button("Criar Conta");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContaButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}
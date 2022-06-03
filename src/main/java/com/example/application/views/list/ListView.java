package com.example.application.views.list;

import com.example.application.data.entity.Contas;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "")
@PageTitle("Contas Correntes")
public class ListView extends VerticalLayout { 
    Grid<Contas> grid = new Grid<>(Contas.class); 
    TextField filterText = new TextField();

    public ListView() {
        addClassName("list-view");
        setSizeFull();
        configureGrid(); 

        add(getToolbar(), grid); 
    }

    private void configureGrid() {
        grid.addClassNames("contas-grid");
        grid.setSizeFull();
        grid.setColumns("banco", "saldo"); 
        grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");       
        grid.getColumns().forEach(col -> col.setAutoWidth(true)); 
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Procurar por nome...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY); 

        Button addContaButton = new Button("Adicionar Conta");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContaButton); 
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}